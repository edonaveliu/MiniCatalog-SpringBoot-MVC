package com.example.minicatalogproject.repositories;

import com.example.minicatalogproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    int countProductByCategory_Id(Integer categoryId);
    List<Product> findAllByCategory_Id(Integer categoryId);
    List<Product> findAllByNameContainingIgnoreCase(String name);
    List<Product> findAllByNameContainingIgnoreCaseAndCategory_Id(String name, Integer categoryId);
}
