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
    private final CategoryCreateCommand categoryCreateCommand;
    private final CategoryUpdateCommand categoryUpdateCommand;

    public Command<Category> createCategoryCommand(CategoryCreateRequest request) {
        return categoryCreateCommand.withRequest(request);
    }

    public Command<Category> updateCategoryCommand(CategoryUpdateRequest request) {
        return categoryUpdateCommand.withRequest(request);
    }
}
