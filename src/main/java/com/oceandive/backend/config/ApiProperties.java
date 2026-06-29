package com.oceandive.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "oceandive.apis")
public class ApiProperties {

    private Api openmeteo = new Api();
    private Api obis = new Api();
    private Api gebco = new Api();

    @Getter
    @Setter
    public static class Api {
        private String baseUrl;
    }
}
