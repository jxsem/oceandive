package com.oceandive.backend.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthProviderRepository
        extends JpaRepository<UserAuthProvider, UUID> {

    Optional<UserAuthProvider> findByProviderUserId(String providerUserId);

}
