package com.yangyag.msa.category.service;

import com.yangyag.msa.category.model.entity.Category;

import java.util.List;

public interface CategoryQueryService {
    Category getCategory(Long id);
    List<Category> getCategoryList();
}
