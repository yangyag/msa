package com.yangyag.msa.category.service;

import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.dto.CategoryDeleteRequest;
import com.yangyag.msa.category.model.dto.CategoryUpdateRequest;
import com.yangyag.msa.category.model.entity.Category;

public interface CategoryCommandService {
    Category create(CategoryCreateRequest request);

    void update(CategoryUpdateRequest request);

    void delete(CategoryDeleteRequest request);
}
