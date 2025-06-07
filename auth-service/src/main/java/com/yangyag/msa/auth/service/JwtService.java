package com.yangyag.msa.auth.service;

import com.yangyag.msa.auth.model.entity.Role;
import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateToken(String userId, String username, Role userRole);

    boolean validateToken(String token);

    String getUsernameFromToken(String token);

    String getUserIdFromToken(String token);

    String getRoleFromToken(String token);

    Claims getAllClaimsFromToken(String token);
}
