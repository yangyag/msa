package com.yangyag.msa.auth.controller;

import com.yangyag.msa.auth.model.dto.AuthResponse;
import com.yangyag.msa.auth.model.dto.LoginRequest;
import com.yangyag.msa.auth.service.AuthQueryService;
import com.yangyag.msa.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthQueryService authQueryService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token =
                authQueryService.authenticate(loginRequest.getUserId(), loginRequest.getPassword());

        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<AuthResponse> validateToken(@RequestBody String token) {
        boolean isValid = jwtService.validateToken(token);
        String userId = jwtService.getUserIdFromToken(token);
        String role = jwtService.getRoleFromToken(token);

        return ResponseEntity.ok(
                AuthResponse.builder().valid(isValid).userId(userId).role(role).build());
    }
}
