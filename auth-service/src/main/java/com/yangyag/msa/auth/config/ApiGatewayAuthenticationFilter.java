package com.yangyag.msa.auth.config;

import com.yangyag.msa.auth.model.entity.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
public class ApiGatewayAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String userId = request.getHeader("X-Auth-User-Id");
        String userRole = request.getHeader("X-Auth-User-Role");

        log.debug("[debug] userId: {}, userRole: {}", userId, userRole);

        if (userId != null && userRole != null) {
            try {
                var role = Role.valueOf(userRole.toUpperCase());
                var simpleGrantedAuthority = new SimpleGrantedAuthority(role.name());
                var simpleGrantedAuthorities = Collections.singletonList(simpleGrantedAuthority);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userId, null, simpleGrantedAuthorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (IllegalArgumentException e) {
                log.warn("Invalid role: {}", userRole);
            }
        }

        filterChain.doFilter(request, response);
    }
}
