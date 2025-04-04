/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facturacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
        registry.addMapping("/api/**")
            .allowedOrigins(
                "https://*.app.github.dev",
                "https://*.github.dev"
            )
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(3600);
    }
    
   @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf().disable()  // Desactiva CSRF para pruebas, pero actívalo en producción
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/productos").permitAll() // Permitir acceso sin autenticación
            .anyRequest().authenticated() // Resto requiere autenticación
        )
        .formLogin(form -> form
            .loginPage("/login") // Página de login (debe existir)
            .failureUrl("/login?error=true") // Redirigir en error
            .permitAll()
        )
        .build();
}

}
