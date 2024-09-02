package com.yangyag.msa.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ApiGatewayAuthenticationFilter apiGatewayAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authz ->
                                authz.requestMatchers("/api/auth/**")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.POST, "/api/users")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.GET, "/api/users")
                                        .authenticated()
                                        .requestMatchers(HttpMethod.PATCH, "/api/users")
                                        .authenticated()
                                        .requestMatchers(HttpMethod.DELETE, "/api/users")
                                        .authenticated()
                                        .anyRequest()
                                        .authenticated())
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // JWT 필터
        http.addFilterBefore(
                apiGatewayAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
