package com.yangyag.msa.category.controller;

import com.yangyag.msa.category.config.TestSecurityConfig;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.service.CategoryQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryQueryController.class)
@ContextConfiguration(classes = {TestSecurityConfig.class})
class CategoryQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryQueryService categoryQueryService;

    @Test
    void shouldReturnCategoryWhenRequestWithValidId() throws Exception {
        Category category = Category.builder()
                .id(1L)
                .name("의류")
                .build();

        // given
        given(categoryQueryService.getCategory(any())).willReturn(category);


        // when & then
        mockMvc.perform(get("/api/categories/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("의류"));
    }
}