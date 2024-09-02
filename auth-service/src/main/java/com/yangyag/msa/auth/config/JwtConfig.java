package com.yangyag.msa.auth.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public Key jwtSecretKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
}
