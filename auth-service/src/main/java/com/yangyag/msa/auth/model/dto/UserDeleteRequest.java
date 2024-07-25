package com.yangyag.msa.auth.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDeleteRequest {
    private String userId;
}
