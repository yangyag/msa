package com.yangyag.msa.category.factory;

import com.yangyag.msa.category.command.CategoryCreateCommand;
import com.yangyag.msa.category.command.CategoryUpdateCommand;
import com.yangyag.msa.category.command.Command;
import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.dto.CategoryUpdateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCommandFactory {
    private CategoryRepository repository;

    public Command<Category> createCategoryCommand(CategoryCreateRequest request) {
        return new CategoryCreateCommand(request, repository);
    }

    public Command<Category> updateCategoryCommand(CategoryUpdateRequest request) {
        return new CategoryUpdateCommand(request, repository);
    }
}
