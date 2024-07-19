package com.yangyag.msa.api.security;

import com.yangyag.msa.api.model.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class JwtAuthenticationFilterFactory extends AbstractGatewayFilterFactory<JwtAuthenticationFilterFactory.Config> {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilterFactory.class);
    private final WebClient.Builder webClientBuilder;

    public JwtAuthenticationFilterFactory(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            String authHeader = request.getHeaders().getFirst("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                log.warn("Authorization 헤더가 없거나 잘못 되었습니다.");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7);

            return webClientBuilder.build()
                    .post()
                    .uri("http://auth-service/api/auth/validate")
                    .bodyValue(token)
                    .retrieve()
                    .bodyToMono(AuthResponse.class)
                    .timeout(Duration.ofSeconds(10))
                    .flatMap(authResponse -> {
                        if (authResponse.isValid()) {
                            log.info("JWT 유효성 검증 성공했습니다. User : {}", authResponse.getUsername());
                            ServerHttpRequest modifiedRequest = request.mutate()
                                    .header("X-Auth-Username", authResponse.getUsername())
                                    .build();
                            return chain.filter(exchange.mutate().request(modifiedRequest).build());
                        } else {
                            log.warn("JWT 유효성 검증에 실패 하였습니다.");
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        }
                    })
                    .onErrorResume(e -> {
                        log.error("JWT 유효성 검증 중 에러가 발생하였습니다. 에러 내용 : {}", e.getMessage());
                        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                        return exchange.getResponse().setComplete();
                    });
        };
    }

    public static class Config {
        // Configuration properties if needed
    }
}