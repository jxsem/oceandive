package com.oceandive.backend.user;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data @Builder
public class UserDTO {
    private String email;
    private String displayName;
    private String bio;
    private String country;
    private ExperienceLevel experienceLevel;
    private PlanType planType;
    private LocalDateTime lastLoginAt;

}
