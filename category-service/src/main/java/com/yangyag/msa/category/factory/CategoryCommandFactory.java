package com.yangyag.msa.category.factory;

import com.yangyag.msa.category.command.*;
import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.dto.CategoryDeleteRequest;
import com.yangyag.msa.category.model.dto.CategoryUpdateRequest;
import com.yangyag.msa.category.model.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCommandFactory {
    private final CategoryCreateCommand categoryCreateCommand;
    private final CategoryUpdateCommand categoryUpdateCommand;
    private final CategoryDeleteCommand categoryDeleteCommand;

    public Command<Category> createCategoryCommand(CategoryCreateRequest request) {
        return categoryCreateCommand.withRequest(request);
    }

    public Command<Category> updateCategoryCommand(CategoryUpdateRequest request) {
        return categoryUpdateCommand.withRequest(request);
    }

    public Command<Category> deleteCategoryCommand(CategoryDeleteRequest request) {
        return categoryDeleteCommand.withRequest(request);
    }
}
