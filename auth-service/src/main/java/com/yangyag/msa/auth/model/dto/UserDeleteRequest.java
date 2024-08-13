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
public class UserDeleteRequest {
    private String userId;
    private Role role;

    public void setRole(Role role) {
        this.role = role;
    }
}
