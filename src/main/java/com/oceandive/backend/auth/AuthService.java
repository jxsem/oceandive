package com.oceandive.backend.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.oceandive.backend.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserAuthProviderRepository userAuthProviderRepository;
    private final GoogleIdTokenVerifier googleTokenVerifier;

    @Transactional
    public String loginWithGoogle(String idToken)
            throws GeneralSecurityException, IOException {

        // 1. Verificar token Google
        GoogleIdToken googleToken;
        try {
            googleToken = googleTokenVerifier.verify(idToken);
        } catch (Exception e) {
            throw new RuntimeException("Invalid Google ID Token");
        }

        if (googleToken == null) {
            throw new RuntimeException("Invalid Google ID Token");
        }

        GoogleIdToken.Payload payload = googleToken.getPayload();

        String providerUserId = payload.getSubject();
        String email = payload.getEmail();
        String name = (String) payload.get("name");
        String picture = (String) payload.get("picture");

        // 2. Buscar provider
        Optional<UserAuthProvider> providerOpt =
                userAuthProviderRepository.findByProviderUserId(providerUserId);

        User user;

        if (providerOpt.isPresent()) {
            user = providerOpt.get().getUser();
        } else {

            // 3. Crear usuario
            user = User.builder()
                    .email(email)
                    .displayName(name)
                    .avatarUrl(picture)
                    .emailVerified(true)
                    .planType(PlanType.TRIAL)
                    .trialUsed(false)
                    .isBanned(false)
                    .build();

            user = userRepository.save(user);

            // 4. Crear provider
            UserAuthProvider provider = UserAuthProvider.builder()
                    .user(user)
                    .provider(AuthProvider.GOOGLE)
                    .providerUserId(providerUserId)
                    .email(email)
                    .build();

            userAuthProviderRepository.save(provider);
        }

        // 5. Generar JWT
        return jwtService.generateToken(user);
    }
}
