package com.yangyag.msa.auth.model.dto;

import com.yangyag.msa.auth.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private String username;
    private String userId;
    private String password;
    private String email;
    private Role role;
}
