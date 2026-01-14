package com.example.minicatalogproject.mappers;

import com.example.minicatalogproject.dtos.category.CategoryDto;
import com.example.minicatalogproject.dtos.category.CreateCategoryRequest;
import com.example.minicatalogproject.dtos.category.UpdateCategoryRequest;
import com.example.minicatalogproject.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryDto categoryDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toEntity(CreateCategoryRequest categoryRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateEntityFromDto(UpdateCategoryRequest categoryRequest, @MappingTarget Category category);
}
