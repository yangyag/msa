package com.yangyag.msa.category.service.impl;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import com.yangyag.msa.category.command.Command;
import com.yangyag.msa.category.factory.CategoryCommandFactory;
import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.dto.CategoryUpdateRequest;
import com.yangyag.msa.category.model.dto.CategoryDeleteRequest;
import com.yangyag.msa.category.model.entity.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryCommandServiceImplTest {
    @Mock private Command<Category> command;

    @Mock private CategoryCommandFactory factory;

    @InjectMocks private CategoryCommandServiceImpl categoryCommandService;

    @Test
    void shouldCreateCategoryWhenValidRequest() {
        CategoryCreateRequest request =
                CategoryCreateRequest.builder().name("의류").parentId(0L).build();

        Category category =
                Category.builder().name(request.getName()).parentId(request.getParentId()).build();

        // given
        given(factory.createCategoryCommand(request)).willReturn(command);
        given(command.execute()).willReturn(category);

        // when
        categoryCommandService.create(request);

        // then
        then(factory).should().createCategoryCommand(request);
    }

    @Test
    void shouldUpdateCategoryWhenValidRequest() {
        CategoryUpdateRequest request = CategoryUpdateRequest.builder().id(1L).name("신발").build();

        Category category = Category.builder().id(request.getId()).name(request.getName()).build();

        // given
        given(factory.updateCategoryCommand(request)).willReturn(command);
        given(command.execute()).willReturn(category);

        // when
        categoryCommandService.update(request);

        // then
        then(factory).should().updateCategoryCommand(request);
        then(command).should().execute();
    }

    @Test
    void shouldDeleteCategoryWhenValidRequest() {
        CategoryDeleteRequest request = mock(CategoryDeleteRequest.class);

        Category category = Category.builder().build();

        // given
        given(factory.deleteCategoryCommand(request)).willReturn(command);
        given(command.execute()).willReturn(category);

        // when
        categoryCommandService.delete(request);

        // then
        then(factory).should().deleteCategoryCommand(request);
        then(command).should().execute();
    }
}
