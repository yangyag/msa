package com.yangyag.msa.category.model.dto;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CategoryUpdateRequest {
    private Long id;
    private String name;
}
