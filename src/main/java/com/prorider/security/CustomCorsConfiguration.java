package com.prorider.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CustomCorsConfiguration {
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfig = new CorsConfiguration();
        /// Cors Urls
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:5783", ""));
        /// Headers
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        /// Methods
        corsConfig.addAllowedMethod(CorsConfiguration.ALL);
        /// Spring Security, cookies etc.
        corsConfig.setAllowCredentials(true);
        /// Register Configuration Routes
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return new CorsFilter(source);
    }

}
