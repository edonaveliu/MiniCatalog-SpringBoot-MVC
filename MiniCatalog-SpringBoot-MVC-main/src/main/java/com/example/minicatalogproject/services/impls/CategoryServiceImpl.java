package com.example.minicatalogproject.services.impls;

import com.example.minicatalogproject.dtos.category.CategoryDto;
import com.example.minicatalogproject.dtos.category.CreateCategoryRequest;
import com.example.minicatalogproject.dtos.category.UpdateCategoryRequest;
import com.example.minicatalogproject.entities.Category;
import com.example.minicatalogproject.mappers.CategoryMapper;
import com.example.minicatalogproject.repositories.CategoryRepository;
import com.example.minicatalogproject.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto create(CreateCategoryRequest categoryRequest) {
        Category categoryEntity = categoryMapper.toEntity(categoryRequest);
        Category createdCategory = categoryRepository.save(categoryEntity);
        return categoryMapper.toDto(createdCategory);
    }

    @Override
    public CategoryDto findById(Integer id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElse(null);
    }

    @Override
    public CategoryDto update(Integer id, UpdateCategoryRequest categoryRequest) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryMapper.updateEntityFromDto(categoryRequest, category);
                    return categoryRepository.save(category);
                })
                .map(categoryMapper::toDto)
                .orElse(null);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
