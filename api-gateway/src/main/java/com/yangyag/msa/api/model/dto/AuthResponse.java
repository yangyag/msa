package com.yangyag.msa.api.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthResponse {
    private boolean valid;
    private String username;
    private String userId;
    private Role role;
}