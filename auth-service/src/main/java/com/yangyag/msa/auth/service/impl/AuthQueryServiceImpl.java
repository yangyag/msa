package com.yangyag.msa.auth.service.impl;

import com.yangyag.msa.auth.service.AuthQueryService;
import com.yangyag.msa.auth.service.JwtService;
import com.yangyag.msa.auth.service.UserQueryService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthQueryServiceImpl implements AuthQueryService {
    private final JwtService jwtService;
    private final UserQueryService userQueryService;

    @Override
    public String authenticate(String userId, String password) {
        return Optional.ofNullable(userQueryService.findByUserIdAndPassword(userId, password))
                .map(
                        user ->
                                jwtService.generateToken(
                                        user.getUserId(), user.getUsername(), user.getRole()))
                .orElseThrow(() -> new BadCredentialsException("Invalid userId or password"));
    }
}
