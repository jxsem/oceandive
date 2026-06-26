package com.oceandive.backend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/google")
    public ResponseEntity<AuthResponse> loginWithGoogle(
        @RequestBody GoogleAuthRequest request) throws Exception 
    {

        String jwt = authService.loginWithGoogle(request.getIdToken());

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}