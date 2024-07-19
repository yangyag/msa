package com.yangyag.msa.auth.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequest {
    private String username;
    private String password;
}
