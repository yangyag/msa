package com.yangyag.msa.category.controller;

import com.yangyag.msa.category.model.dto.CategoryCreateRequest;
import com.yangyag.msa.category.model.dto.CategoryUpdateRequest;
import com.yangyag.msa.category.service.CategoryCreateService;
import com.yangyag.msa.category.service.CategoryUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryCommandController {
    private final CategoryCreateService categoryCreateService;
    private final CategoryUpdateService categoryUpdateService;

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoryCreateRequest request) {
        categoryCreateService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Object> updateCategory(@RequestBody CategoryUpdateRequest request) {
        categoryUpdateService.update(request);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
