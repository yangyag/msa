package com.yangyag.msa.category.controller;

import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.service.CategoryCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryCreateService categoryCreateService;

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoryCreateRequest request) {
        categoryCreateService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
