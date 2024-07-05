package com.yangyag.msa.category.service;

import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.entity.Category;

public interface CategoryCreateService {
    Category create(CategoryCreateRequest request);
}
