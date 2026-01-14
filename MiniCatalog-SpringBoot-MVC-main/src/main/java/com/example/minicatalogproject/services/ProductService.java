package com.example.minicatalogproject.services;

import com.example.minicatalogproject.dtos.product.CreateProductRequest;
import com.example.minicatalogproject.dtos.product.ProductDto;
import com.example.minicatalogproject.dtos.product.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    List<ProductDto> findAllByName(String name);
    List<ProductDto> findAllByCategoryId(Integer categoryId);
    List<ProductDto> findAllByNameAndCategoryId(String name, Integer categoryId);
    int countByCategoryId(Integer categoryId);
    ProductDto findById(Integer id);
    ProductDto create(CreateProductRequest createProductRequest);
    ProductDto update(Integer id, UpdateProductRequest updateProductRequest);
    void delete(Integer id);
}
