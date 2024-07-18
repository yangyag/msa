package com.yangyag.msa.api.service;

public interface JwtService {
    String generateToken(String username);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
}
