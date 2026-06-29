package com.oceandive.backend.oceandata.openmeteo;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oceandive.backend.config.ApiProperties;

import lombok.Getter;

@Service
public class OpenMeteoService {
    private final WebClient webClient;
    private final ApiProperties apiProperties;

    public OpenMeteoService(ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
        this.webClient = WebClient.builder()
            .baseUrl(apiProperties.getOpenmeteo().getBaseUrl())
            .build();
    }

    public OpenMeteoDTO getMarineConditions(double latitude, double longitude) {
        
        // 1. Llamar a la API
        OpenMeteoResponse response = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/v1/marine")
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("current", "wave_height,wave_direction,sea_surface_temperature")
                .build())
            .retrieve()
            .bodyToMono(OpenMeteoResponse.class)
            .block();

        // 2. Verificar que llegó algo
        if (response == null || response.getCurrent() == null) {
            throw new RuntimeException("No marine data available for this location");
        }

        // 3. Sacar el Current
        Current current = response.getCurrent();

        // 4. Construir y devolver el DTO
        return OpenMeteoDTO.builder()
            .waveHeight(current.getWaveHeight())
            .waveDirection(current.getWaveDirection())
            .seaSurfaceTemperature(current.getSeaSurfaceTemperature())
            .measuredAt(LocalDateTime.parse(current.getTime()))
            .build();
    }

    // clases internas aquí dentro
    @Getter
    static class OpenMeteoResponse {
        private Current current;
    }

    @Getter
    static class Current {
        @JsonProperty("wave_height")
        private double waveHeight;
        @JsonProperty("wave_direction")
        private int waveDirection;
        @JsonProperty("sea_surface_temperature")
        private double seaSurfaceTemperature;
        private String time;
    }
}
