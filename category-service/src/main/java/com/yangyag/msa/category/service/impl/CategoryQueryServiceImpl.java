package com.yangyag.msa.category.service.impl;

import com.yangyag.msa.category.exception.EntityNotFoundException;
import com.yangyag.msa.category.model.entity.Category;
import com.yangyag.msa.category.repository.CategoryRepository;
import com.yangyag.msa.category.service.CategoryQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {
    private final CategoryRepository repository;

    @Override
    public Category getCategory(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category", id));
    }

    @Override
    public List<Category> getCategoryList() {
        return repository.findAll();
    }
}
