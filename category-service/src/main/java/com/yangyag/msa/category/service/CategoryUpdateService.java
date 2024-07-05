package com.yangyag.msa.category.service;

import com.yangyag.msa.category.model.dto.CategoryUpdateRequest;
import com.yangyag.msa.category.model.entity.Category;

public interface CategoryUpdateService {
    Category update(CategoryUpdateRequest request);
}
