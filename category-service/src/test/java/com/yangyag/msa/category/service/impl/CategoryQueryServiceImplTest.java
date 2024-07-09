package com.yangyag.msa.category.service.impl;

import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CategoryQueryServiceImplTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryQueryServiceImpl service;

    @Test
    void shouldReturnCategoryWhenValidRequest() {
        Category category = Category.builder()
                .id(1L)
                .name("의류")
                .build();

        //given
        given(repository.findById(any())).willReturn(Optional.ofNullable(category));

        //when
        Category result = service.getCategory(1L);

        //then
        then(repository).should().findById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldReturnCategoryListWhenValidRequest() {
        Category category1 = Category.builder()
                .id(1L)
                .name("의류")
                .build();

        Category category2 = Category.builder()
                .id(2L)
                .name("신발")
                .build();

        //given
        given(repository.findAll()).willReturn(List.of(category1, category2));

        //when
        List<Category> list = service.getCategoryList();

        //then
        then(repository).should().findAll();
        assertEquals(2, list.size());

        assertTrue(list.stream().anyMatch(category -> category.getId().equals(1L)));
    }
}