package com.yangyag.msa.category.controller;

import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.service.CategoryQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryQueryController {

    private final CategoryQueryService categoryQueryService;

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        var result = categoryQueryService.getCategory(id);

        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategoryList() {
        var list = categoryQueryService.getCategoryList();

        return ResponseEntity.ok(list);
    }
}
