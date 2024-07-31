package com.yangyag.msa.auth.service;

public interface JwtService {
    String generateToken(String username, String userId);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
    String getUserIdFromToken(String token);
}
