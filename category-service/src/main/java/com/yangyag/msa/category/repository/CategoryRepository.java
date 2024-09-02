package com.yangyag.msa.category.repository;

import com.yangyag.msa.category.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
