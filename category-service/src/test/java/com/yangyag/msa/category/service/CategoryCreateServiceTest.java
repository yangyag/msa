package com.yangyag.msa.category.service;

import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CategoryCreateServiceTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryCreateService service;

    @Test
    void shouldCreateCategoryWhenValidRequest() {
        //given
        CategoryCreateRequest request = CategoryCreateRequest.builder()
                .name("의류")
                .parentId(0L)
                .depth(0L)
                .build();

        Category category = Category.builder()
                .name(request.getName())
                .parentId(request.getParentId())
                .depth(request.getDepth())
                .build();

        given(repository.save(any(Category.class))).willReturn(category);

        //when
        service.create(request);

        //then
        then(repository).should().save(any(Category.class));
    }
}