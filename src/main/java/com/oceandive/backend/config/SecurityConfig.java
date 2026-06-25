package com.oceandive.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Es una API REST: se deshabilita CSRF por completo
            .csrf(csrf -> csrf.disable())
            
            // 2. Definición de accesos a los endpoints
            .authorizeHttpRequests(auth -> auth
                // Opción A: Permitir libre acceso a TODO (Ideal para pruebas locales rápidas)
                .anyRequest().permitAll()
                
                // Opción B: Solo proteger el resto y solo abrir rutas de test, descomenta abajo:
                // .requestMatchers("/api/v1/publico/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                // .anyRequest().authenticated()
            )
            
            // 3. API REST Stateless: Obliga a Spring a no crear sesiones web en el servidor
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS
            ));

        return http.build();
    }
}