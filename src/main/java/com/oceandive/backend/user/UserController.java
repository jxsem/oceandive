package com.oceandive.backend.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<UserDTO> findByEmail(@RequestParam String email) {
        return userService.findByEmail(email)
            .map(user -> UserDTO.builder()
                .email(user.getEmail())
                .displayName(user.getDisplayName())
                .bio(user.getBio())
                .country(user.getCountry())
                .experienceLevel(user.getExperienceLevel())
                .planType(user.getPlanType())
                .lastLoginAt(user.getLastLoginAt())
                .build())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
}
