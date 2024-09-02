package com.yangyag.msa.auth.service.impl;

import com.yangyag.msa.auth.model.entity.Role;
import com.yangyag.msa.auth.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final Key jwtSecretKey;

    @Override
    public String generateToken(String userId, String username, Role userRole) {
        long expirationTime = 1000 * 60 * 60; // 1 hour
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("username", username)
                .claim("role", userRole)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(jwtSecretKey)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String getUsernameFromToken(String token) {
        return this.getAllClaimsFromToken(token).get("username", String.class);
    }

    @Override
    public String getUserIdFromToken(String token) {
        return this.getAllClaimsFromToken(token).get("userId", String.class);
    }

    @Override
    public String getRoleFromToken(String token) {
        return this.getAllClaimsFromToken(token).get("role", String.class);
    }

    @Override
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
