package com.yangyag.msa.category.service;

import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryCreateService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public Category create(CategoryCreateRequest request) {

        Category category = Category.builder()
                .name(request.getName())
                .depth(request.getDepth())
                .parentId(request.getParentId())
                .build();

        return categoryRepository.save(category);
    }
}
