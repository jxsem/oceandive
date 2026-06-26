package com.oceandive.backend.user;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    // Identidad
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = false)
    private boolean emailVerified;

    private String avatarUrl;

    private String bio;

    private String country;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    // Plan
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanType planType;

    @Enumerated(EnumType.STRING)
    private PlanStatus planStatus;

    private LocalDateTime planStartedAt;

    private LocalDateTime planExpiresAt;

    @Column(nullable = false)
    private boolean trialUsed;

    // Estado cuenta
    @Column(nullable = false)
    private boolean isBanned;

    private LocalDateTime lastLoginAt;

    // Auditoría
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;
}