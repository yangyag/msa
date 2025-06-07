package com.yangyag.msa.category.command;

import com.yangyag.msa.category.model.dto.CategoryUpdateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryUpdateCommand implements Command<Category> {
    private final CategoryRepository repository;

    public Command<Category> withRequest(CategoryUpdateRequest request) {
        return () ->
                repository
                        .findById(request.getId())
                        .map(
                                category -> {
                                    category.setName(request.getName());
                                    return repository.save(category);
                                })
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                String.format(
                                                        "%s 값을 찾을 수 없습니다.", request.getId())));
    }

    @Override
    public Category execute() {
        throw new IllegalStateException("이 메소드는 직접 실행할 수 없습니다. withRequest() 를 사용하세요.");
    }
}
