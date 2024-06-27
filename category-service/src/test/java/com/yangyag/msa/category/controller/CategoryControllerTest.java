package com.yangyag.msa.category.controller;

import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.service.CategoryCreateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryCreateService categoryCreateService;

    @Test
    public void createCategory_ShouldReturnCreatedCategory() throws Exception {
        CategoryCreateRequest request = CategoryCreateRequest.builder()
                .name("의류")
                .parentId(0L)
                .depth(0L)
                .build();

        Category category = Category.builder()
                .name(request.getName())
                .depth(request.getDepth())
                .parentId(request.getParentId())
                .build();

        Mockito.when(categoryCreateService.create(Mockito.any(CategoryCreateRequest.class)))
                .thenReturn(category);

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"의류\"}"))
                .andExpect(status().isCreated());
    }
}
