package com.yangyag.msa.category.service.impl;

import com.yangyag.msa.category.factory.CategoryCommandFactory;
import com.yangyag.msa.category.model.dto.CategoryUpdateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.service.CategoryUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryUpdateServiceImpl implements CategoryUpdateService {
    private final CategoryCommandFactory factory;

    @Override
    public Category update(CategoryUpdateRequest request) {
        return factory.updateCategoryCommand(request).execute();
    }
}
