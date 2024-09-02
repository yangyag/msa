package com.yangyag.msa.category;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.dto.CategoryDeleteRequest;
import com.yangyag.msa.category.model.dto.CategoryUpdateRequest;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryIntegrationTest {
    @Autowired private MockMvc mockMvc;

    @Autowired private CategoryRepository repository;

    @Autowired private ObjectMapper objectMapper;

    private Category category1;
    private Category category2;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        this.saveCategories();
    }

    private void saveCategories() {
        category1 = repository.save(Category.builder().name("의류").build());
        category2 = repository.save(Category.builder().name("전자기기").build());
    }

    @Nested
    class GetTests {
        @Test
        void shouldReturnCategoryWhenGetRequestWithExistingId() throws Exception {
            mockMvc.perform(get("/api/categories/{id}", category1.getId()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(category1.getId()))
                    .andExpect(jsonPath("$.name").value(category1.getName()));
        }

        @Test
        void shouldReturnAllCategoriesWhenGetRequestWithoutId() throws Exception {
            mockMvc.perform(get("/api/categories"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].id").value(category1.getId()))
                    .andExpect(jsonPath("$[0].name").value(category1.getName()))
                    .andExpect(jsonPath("$[1].id").value(category2.getId()))
                    .andExpect(jsonPath("$[1].name").value(category2.getName()));
        }
    }

    @Nested
    class PostTests {
        @Test
        void shouldCreateCategory() throws Exception {
            CategoryCreateRequest request = CategoryCreateRequest.builder().name("의류").build();

            mockMvc.perform(
                            post("/api/categories")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated());
        }
    }

    @Nested
    class PatchTests {
        @Test
        void shouldUpdateCategoryWhenValidRequest() throws Exception {
            CategoryUpdateRequest request =
                    CategoryUpdateRequest.builder().name("신발").id(category1.getId()).build();

            mockMvc.perform(
                            patch("/api/categories")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isNoContent());
        }
    }

    @Nested
    class DeleteTests {
        @Test
        void shouldDeleteCategoryWhenGetRequestWithExistingId() throws Exception {
            Category category = this.createTempCategoryForDeleteTest();

            CategoryDeleteRequest request =
                    CategoryDeleteRequest.builder().id(category.getId()).build();

            mockMvc.perform(
                            delete("/api/categories")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isNoContent());
        }

        private Category createTempCategoryForDeleteTest() {
            return repository.save(Category.builder().name("가전제품").build());
        }
    }
}
