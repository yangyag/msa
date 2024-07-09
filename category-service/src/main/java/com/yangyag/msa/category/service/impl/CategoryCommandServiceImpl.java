package com.yangyag.msa.category.service.impl;

import com.yangyag.msa.category.factory.CategoryCommandFactory;
import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.dto.CategoryDeleteRequest;
import com.yangyag.msa.category.model.dto.CategoryUpdateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.service.CategoryCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryCommandServiceImpl implements CategoryCommandService {
    private final CategoryCommandFactory factory;

    @Transactional
    @Override
    public Category create(CategoryCreateRequest request) {
        return factory.createCategoryCommand(request).execute();
    }

    @Transactional
    @Override
    public void update(CategoryUpdateRequest request) {
        factory.updateCategoryCommand(request).execute();
    }

    @Transactional
    @Override
    public void delete(CategoryDeleteRequest request) {
        factory.deleteCategoryCommand(request).execute();
    }
}
