package com.yangyag.msa.api.service.impl;

import com.yangyag.msa.api.service.AuthService;
import com.yangyag.msa.api.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;

    @Override
    public String authenticate(String username, String password) {
        if ("testuser".equals(username) && "testpassword".equals(password)) {
            return jwtService.generateToken(username);
        }

        return null;
    }
}
