package com.yangyag.msa.category.service;

import com.yangyag.msa.category.command.CategoryCreateCommand;
import com.yangyag.msa.category.command.Command;
import com.yangyag.msa.category.factory.CategoryCommandFactory;
import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.service.impl.CategoryCreateServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CategoryCreateServiceTest {

    @Mock
    private Command<Category> command;

    @Mock
    private CategoryCommandFactory factory;

    @InjectMocks
    private CategoryCreateServiceImpl service;

    @Test
    void shouldCreateCategoryWhenValidRequest() {
        CategoryCreateRequest request = CategoryCreateRequest.builder()
                .name("의류")
                .parentId(0L)
                .build();

        Category category = Category.builder()
                .name(request.getName())
                .parentId(request.getParentId())
                .build();

        //given
        given(factory.createCategoryCommand(request)).willReturn(command);
        given(command.execute()).willReturn(category);

        //when
        service.create(request);

        //then
        then(factory).should().createCategoryCommand(request);
    }
}