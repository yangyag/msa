package com.yangyag.msa.category.service.impl;

import com.yangyag.msa.category.factory.CategoryCommandFactory;
import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.service.CategoryCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryCreateServiceImpl implements CategoryCreateService {
    private final CategoryCommandFactory factory;

    @Transactional
    public Category create(CategoryCreateRequest request) {
        return factory.createCategoryCommand(request).execute();
    }

}
