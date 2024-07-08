package com.yangyag.msa.category.service;

import com.yangyag.msa.category.command.Command;
import com.yangyag.msa.category.factory.CategoryCommandFactory;
import com.yangyag.msa.category.model.dto.CategoryUpdateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.service.impl.CategoryUpdateServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CategoryUpdateServiceTest {

    @Mock
    private Command<Category> command;

    @Mock
    private CategoryCommandFactory factory;

    @InjectMocks
    private CategoryUpdateServiceImpl service;

    @Test
    void shouldCreateCategoryWhenValidRequest() {
        CategoryUpdateRequest request = CategoryUpdateRequest.builder()
                .id(1L)
                .name("신발")
                .build();

        Category category = Category.builder()
                .id(request.getId())
                .name(request.getName())
                .build();

        //given
        given(factory.updateCategoryCommand(request)).willReturn(command);
        given(command.execute()).willReturn(category);

        //when
        service.update(request);

        //then
        then(factory).should().updateCategoryCommand(request);
        then(command).should().execute();
    }
}