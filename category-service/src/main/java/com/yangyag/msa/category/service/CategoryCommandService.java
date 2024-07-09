package com.yangyag.msa.category.service;

import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.dto.CategoryUpdateRequest;
import com.yangyag.msa.category.model.entity.Category;

public interface CategoryCommandService {
    Category create(CategoryCreateRequest request);
    Category update(CategoryUpdateRequest request);
}
