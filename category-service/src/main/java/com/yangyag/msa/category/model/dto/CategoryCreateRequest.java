package com.yangyag.msa.category.model.dto;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CategoryCreateRequest {
    private String name;
    private Long parentId;
}
