package com.example.minicatalogproject.mappers;

import com.example.minicatalogproject.dtos.category.CategoryDto;
import com.example.minicatalogproject.dtos.category.CreateCategoryRequest;
import com.example.minicatalogproject.dtos.category.UpdateCategoryRequest;
import com.example.minicatalogproject.entities.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-04T01:35:49+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.18 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setName( category.getName() );
        categoryDto.setDescription( category.getDescription() );

        return categoryDto;
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( categoryDto.getName() );
        category.description( categoryDto.getDescription() );

        return category.build();
    }

    @Override
    public Category toEntity(CreateCategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( categoryRequest.getName() );
        category.description( categoryRequest.getDescription() );

        return category.build();
    }

    @Override
    public void updateEntityFromDto(UpdateCategoryRequest categoryRequest, Category category) {
        if ( categoryRequest == null ) {
            return;
        }

        category.setName( categoryRequest.getName() );
        category.setDescription( categoryRequest.getDescription() );
    }
}
