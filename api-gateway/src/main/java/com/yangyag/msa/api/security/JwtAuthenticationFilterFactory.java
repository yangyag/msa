package com.yangyag.msa.api.security;

import com.yangyag.msa.api.model.dto.AuthResponse;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilterFactory
        extends AbstractGatewayFilterFactory<JwtAuthenticationFilterFactory.Config> {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilterFactory.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String AUTH_USER_HEADER = "X-Auth-User-Id";
    private static final String AUTH_USER_ROLE_HEADER = "X-Auth-User-Role";
    private static final String AUTH_SERVICE_URL = "http://auth-service/api/auth/validate";
    private static final Duration TIMEOUT_DURATION = Duration.ofSeconds(10);

    private final WebClient.Builder webClientBuilder;

    public JwtAuthenticationFilterFactory(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String authHeader = request.getHeaders().getFirst(AUTHORIZATION_HEADER);

            if (!isValidAuthHeader(authHeader)) {
                return handleUnauthorized(exchange);
            }

            String token = extractToken(authHeader);
            return validateToken(token)
                    .flatMap(
                            authResponse ->
                                    handleValidAuthResponse(authResponse, request, exchange, chain))
                    .onErrorResume(e -> handleError(e, exchange));
        };
    }

    private boolean isValidAuthHeader(String authHeader) {
        return authHeader != null && authHeader.startsWith(BEARER_PREFIX);
    }

    private String extractToken(String authHeader) {
        return authHeader.substring(BEARER_PREFIX.length());
    }

    private Mono<AuthResponse> validateToken(String token) {
        return webClientBuilder
                .build()
                .post()
                .uri(AUTH_SERVICE_URL)
                .bodyValue(token)
                .retrieve()
                .bodyToMono(AuthResponse.class)
                .timeout(TIMEOUT_DURATION);
    }

    private Mono<Void> handleValidAuthResponse(
            AuthResponse authResponse,
            ServerHttpRequest request,
            ServerWebExchange exchange,
            GatewayFilterChain chain) {
        if (authResponse.isValid()) {
            log.debug(
                    "JWT 검증 성공. User: {}, Role: {}",
                    authResponse.getUserId(),
                    authResponse.getRole());
            ServerHttpRequest modifiedRequest =
                    request.mutate()
                            .header(AUTH_USER_HEADER, authResponse.getUserId())
                            .header(AUTH_USER_ROLE_HEADER, authResponse.getRole().name())
                            .build();
            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        } else {
            log.warn("JWT 검증 실패");
            return handleUnauthorized(exchange);
        }
    }

    private Mono<Void> handleUnauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    private Mono<Void> handleError(Throwable e, ServerWebExchange exchange) {
        log.error("JWT 검증 도중 오류 발생", e);
        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
        // Configuration properties if needed
    }
}
