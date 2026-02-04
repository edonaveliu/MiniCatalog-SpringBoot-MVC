package com.example.minicatalogproject.mappers;

import com.example.minicatalogproject.dtos.product.CreateProductRequest;
import com.example.minicatalogproject.dtos.product.ProductDto;
import com.example.minicatalogproject.dtos.product.UpdateProductRequest;
import com.example.minicatalogproject.entities.Category;
import com.example.minicatalogproject.entities.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-04T01:35:49+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.18 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setCategoryId( entityCategoryId( entity ) );
        productDto.setCategoryName( entityCategoryName( entity ) );
        productDto.setId( entity.getId() );
        productDto.setName( entity.getName() );
        productDto.setDescription( entity.getDescription() );
        productDto.setPrice( entity.getPrice() );
        productDto.setStock( entity.getStock() );
        productDto.setImage( entity.getImage() );

        return productDto;
    }

    @Override
    public Product toEntity(CreateProductRequest createProductRequest) {
        if ( createProductRequest == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( createProductRequest.getName() );
        product.price( createProductRequest.getPrice() );
        product.stock( createProductRequest.getStock() );
        product.description( createProductRequest.getDescription() );
        product.image( createProductRequest.getImage() );

        return product.build();
    }

    @Override
    public UpdateProductRequest toUpdateRequest(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        UpdateProductRequest updateProductRequest = new UpdateProductRequest();

        updateProductRequest.setName( productDto.getName() );
        updateProductRequest.setDescription( productDto.getDescription() );
        updateProductRequest.setPrice( productDto.getPrice() );
        updateProductRequest.setStock( productDto.getStock() );
        updateProductRequest.setImage( productDto.getImage() );
        updateProductRequest.setCategoryId( productDto.getCategoryId() );

        return updateProductRequest;
    }

    @Override
    public void updateEntityFromDto(UpdateProductRequest dto, Product entity) {
        if ( dto == null ) {
            return;
        }

        entity.setName( dto.getName() );
        entity.setPrice( dto.getPrice() );
        entity.setStock( dto.getStock() );
        entity.setDescription( dto.getDescription() );
        entity.setImage( dto.getImage() );
    }

    private Integer entityCategoryId(Product product) {
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getId();
    }

    private String entityCategoryName(Product product) {
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getName();
    }
}
