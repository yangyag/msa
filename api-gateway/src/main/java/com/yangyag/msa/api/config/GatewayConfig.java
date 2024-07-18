package com.yangyag.msa.api.config;

import com.yangyag.msa.api.security.JwtAuthenticationFilter;
import com.yangyag.msa.api.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService) {
        return new JwtAuthenticationFilter(jwtService);
    }
}