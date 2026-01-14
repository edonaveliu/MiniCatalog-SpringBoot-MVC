package com.example.minicatalogproject.services.impls;

import com.example.minicatalogproject.dtos.product.CreateProductRequest;
import com.example.minicatalogproject.dtos.product.ProductDto;
import com.example.minicatalogproject.dtos.product.UpdateProductRequest;
import com.example.minicatalogproject.entities.Category;
import com.example.minicatalogproject.entities.Product;
import com.example.minicatalogproject.mappers.ProductMapper;
import com.example.minicatalogproject.repositories.CategoryRepository;
import com.example.minicatalogproject.repositories.ProductRepository;
import com.example.minicatalogproject.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductDto> findAllByName(String name) {
        return productRepository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductDto> findAllByCategoryId(Integer categoryId) {
        return productRepository.findAllByCategory_Id(categoryId)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductDto> findAllByNameAndCategoryId(String name, Integer categoryId) {
        return productRepository.findAllByNameContainingIgnoreCaseAndCategory_Id(name, categoryId)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public int countByCategoryId(Integer categoryId) {
        return productRepository.countProductByCategory_Id(categoryId);
    }

    @Override
    public ProductDto findById(Integer id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElse(null);
    }

    @Override
    public ProductDto create(CreateProductRequest createProductRequest) {
        Product product = productMapper.toEntity(createProductRequest);
        Category category = getCategory(createProductRequest.getCategoryId());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto update(Integer id, UpdateProductRequest updateProductRequest) {
        return productRepository.findById(id)
                .map(product -> {
                    productMapper.updateEntityFromDto(updateProductRequest, product);

                    Category category = getCategory(updateProductRequest.getCategoryId());
                    product.setCategory(category);

                    return productRepository.save(product);
                })
                .map(productMapper::toDto)
                .orElse(null);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    private Category getCategory(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
