package com.yangyag.msa.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private Category category1;
    private Category category2;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        this.saveCategories();
    }

    void saveCategories() {
        category1 = repository.save(Category.builder()
                .name("의류")
                .build());
        category2 = repository.save(Category.builder()
                .name("전자기기")
                .build());
    }

    @Test
    void shouldCreateCategory() throws Exception {
        CategoryCreateRequest request = CategoryCreateRequest.builder().name("의류").build();

        //when&then
        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnCategoryWhenGetRequestWithExistingId() throws Exception {
        // when & then
        mockMvc.perform(get("/api/categories/{id}", category1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(category1.getId()))
                .andExpect(jsonPath("$.name").value(category1.getName()));
    }

    @Test
    void shouldReturnAllCategoriesWhenGetRequestWithoutId() throws Exception {
        // when & then
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(category1.getId()))
                .andExpect(jsonPath("$[0].name").value(category1.getName()))
                .andExpect(jsonPath("$[1].id").value(category2.getId()))
                .andExpect(jsonPath("$[1].name").value(category2.getName()));
    }
}