/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facturacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Admin
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/formularios/**").addResourceLocations("file: "+this.form);

    }

@Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins(
            "http://localhost",          // Dominio base
            "http://localhost:80",       // Puerto HTTP estándar
            "http://localhost:5500",    // Typical Live Server port
            "http://localhost:3000"     // React/Vue dev server
        )
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Incluir OPTIONS
        .allowedHeaders(
            "Authorization",
            "Content-Type",
            "Accept",
            "X-Requested-With",
            "Cache-Control"
        )
        .exposedHeaders(
            "Authorization",
            "Content-Disposition",
            "X-Total-Count"  // Útil para paginación
        )
        .allowCredentials(true)
        .maxAge(3600);  // Cache preflight por 1 hora
}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults()) // Habilita CORS con configuración personalizada
                .csrf(csrf -> csrf.disable()) // CSRF deshabilitado para APIs stateless
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        HttpMethod.GET,
                        "/api/productos/**", // Acceso público a GET de productos
                        "/swagger-ui/**", // Documentación API
                        "/v3/api-docs/**" // Especificación OpenAPI
                ).permitAll()
                .requestMatchers(
                        HttpMethod.POST,
                        "/api/auth/**" // Endpoints de autenticación
                ).permitAll()
                .anyRequest().authenticated() // Resto de endpoints requieren autenticación
                )
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // APIs sin estado
                )
                .httpBasic(Customizer.withDefaults()) // Autenticación Basic para pruebas
                .build();
    }
}
