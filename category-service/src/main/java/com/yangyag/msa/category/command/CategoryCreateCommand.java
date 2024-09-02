package com.yangyag.msa.category.command;

import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCreateCommand implements Command<Category> {
    private final CategoryRepository repository;

    public Command<Category> withRequest(CategoryCreateRequest request) {
        return () -> this.saveCategory(request);
    }

    private Category saveCategory(CategoryCreateRequest request) {
        Category category =
                Category.builder().name(request.getName()).parentId(request.getParentId()).build();

        return repository.save(category);
    }

    @Override
    public Category execute() {
        throw new IllegalStateException("이 메소드는 직접 실행할 수 없습니다. withId() 를 사용하세요.");
    }
}
