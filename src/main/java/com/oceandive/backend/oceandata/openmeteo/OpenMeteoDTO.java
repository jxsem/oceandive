package com.oceandive.backend.oceandata.openmeteo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class OpenMeteoDTO {
    private double waveHeight;
    private int waveDirection;
    private double seaSurfaceTemperature;
    private LocalDateTime measuredAt;
}
