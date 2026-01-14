package com.example.minicatalogproject.mappers;

import com.example.minicatalogproject.dtos.product.CreateProductRequest;
import com.example.minicatalogproject.dtos.product.ProductDto;
import com.example.minicatalogproject.dtos.product.UpdateProductRequest;
import com.example.minicatalogproject.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductDto toDto(Product entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(CreateProductRequest createProductRequest);

    UpdateProductRequest toUpdateRequest(ProductDto productDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updateEntityFromDto(UpdateProductRequest dto, @MappingTarget Product entity);
}