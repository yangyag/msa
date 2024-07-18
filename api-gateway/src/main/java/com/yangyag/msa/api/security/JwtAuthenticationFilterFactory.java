package com.yangyag.msa.api.security;

import com.yangyag.msa.api.service.JwtService;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilterFactory extends AbstractGatewayFilterFactory<JwtAuthenticationFilterFactory.Config> {

    private final JwtService jwtService;

    public JwtAuthenticationFilterFactory(JwtService jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            String authHeader = request.getHeaders().getFirst("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                if (jwtService.validateToken(token)) {
                    String username = jwtService.getUsernameFromToken(token);
                    ServerHttpRequest modifiedRequest = request.mutate()
                            .header("X-Auth-Username", username)
                            .build();
                    return chain.filter(exchange.mutate().request(modifiedRequest).build());
                }
            }

            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        };
    }

    public static class Config {
        // 필요한 경우 설정을 여기에 추가
    }
}