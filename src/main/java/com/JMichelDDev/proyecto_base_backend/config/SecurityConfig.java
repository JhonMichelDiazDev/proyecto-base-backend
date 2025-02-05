package com.JMichelDDev.proyecto_base_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.JMichelDDev.proyecto_base_backend.security.JwtAuthenticationFilter;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
            // Configuración de CORS: permite solicitudes desde el frontend (ajusta según necesites)
            .cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(List.of("http://localhost:3000")); // URL del frontend
                config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                config.setAllowedHeaders(List.of("*"));
                return config;
            }))
            // Deshabilitar CSRF usando el método recomendado a partir de Spring Security 6.1
            .csrf(AbstractHttpConfigurer::disable)
            // Configuración de las reglas de autorización:
            .authorizeHttpRequests(auth -> auth
                // Permitir acceso público a endpoints de autenticación
                .requestMatchers("/api/auth/**").permitAll()
                // Permitir acceso público a solicitudes GET a endpoints públicos
                .requestMatchers(HttpMethod.GET, "/api/public/**").permitAll()
                // Cualquier otro endpoint requerirá autenticación
                .anyRequest().authenticated()
            )
            // Configuración del manejo de sesiones: sin mantener sesiones en el servidor
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
        
        // Agrega el filtro JWT antes del filtro de autenticación de Spring
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
