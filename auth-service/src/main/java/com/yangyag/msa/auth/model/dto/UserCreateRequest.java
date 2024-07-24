package com.yangyag.msa.auth.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserCreateRequest {
    private String username;
    private String userId;
    private String password;
    private String email;
}
