package com.yangyag.msa.category.command;

import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCreateCommand implements Command<Category> {
    private CategoryCreateRequest request;
    private final CategoryRepository repository;

    public CategoryCreateCommand withRequest(CategoryCreateRequest request) {
        this.request = request;
        return this;
    }

    @Override
    public Category execute() {
        Category category = Category.builder()
                .name(request.getName())
                .parentId(request.getParentId())
                .build();

        return repository.save(category);
    }
}
