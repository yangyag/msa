package com.yangyag.msa.category.command;

import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryCreateCommand implements Command<Category> {
    private final CategoryCreateRequest request;
    private final CategoryRepository repository;

    @Override
    public Category execute() {
        Category category = Category.builder()
                .name(request.getName())
                .depth(request.getDepth())
                .parentId(request.getParentId())
                .build();

        return repository.save(category);
    }
}
