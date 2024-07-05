package com.yangyag.msa.category.command;

import com.yangyag.msa.category.model.dto.CategoryUpdateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryUpdateCommand implements Command<Category> {
    private final CategoryUpdateRequest request;
    private final CategoryRepository repository;

    @Override
    public Category execute() {
        Category category = Category.builder()
                .id(request.getId())
                .name(request.getName())
                .build();

        return repository.save(category);
    }
}
