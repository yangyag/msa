package com.yangyag.msa.category.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangyag.msa.category.config.TestSecurityConfig;
import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.service.CategoryCommandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CategoryCommandController.class)
@ContextConfiguration(classes = {TestSecurityConfig.class})
public class CategoryCommandControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private CategoryCommandService categoryCommandService;

    @Autowired private ObjectMapper objectMapper;

    @Test
    public void shouldCreateCategory() throws Exception {
        CategoryCreateRequest request = CategoryCreateRequest.builder().name("의류").build();

        // given
        given(categoryCommandService.create(any())).willReturn(mock(Category.class));

        // when&then
        mockMvc.perform(
                        post("/api/categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
}
