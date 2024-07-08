package com.yangyag.msa.category.command;

import com.yangyag.msa.category.model.dto.CategoryUpdateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryUpdateCommand implements Command<Category> {
    private CategoryUpdateRequest request;
    private final CategoryRepository repository;

    public CategoryUpdateCommand withRequest(final CategoryUpdateRequest request) {
        this.request = request;
        return this;
    }

    @Override
    public Category execute() {
        Category category = repository.findById(request.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("%s 값을 찾을 수 없습니다.", request.getId())));

        category.setName(request.getName());

        return repository.save(category);
    }
}
