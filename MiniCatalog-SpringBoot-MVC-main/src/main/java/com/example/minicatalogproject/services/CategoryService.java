package com.example.minicatalogproject.services;

import com.example.minicatalogproject.dtos.category.CategoryDto;
import com.example.minicatalogproject.dtos.category.CreateCategoryRequest;
import com.example.minicatalogproject.dtos.category.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
    CategoryDto create(CreateCategoryRequest categoryRequest);
    CategoryDto findById(Integer id);
    CategoryDto update(Integer id, UpdateCategoryRequest categoryRequest);
    void delete(Integer id);
}
