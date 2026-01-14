package com.example.minicatalogproject.controllers;

import com.example.minicatalogproject.dtos.category.CategoryDto;
import com.example.minicatalogproject.dtos.product.ProductDto;
import com.example.minicatalogproject.services.CategoryService;
import com.example.minicatalogproject.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public String index(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) Integer categoryId,
            Model model) {

        List<ProductDto> products;

        if (searchText != null && !searchText.isBlank() && categoryId != null) {
            products = productService.findAllByNameAndCategoryId(searchText, categoryId);
        } else if (searchText != null && !searchText.isBlank()) {
            products = productService.findAllByName(searchText);
        } else if (categoryId != null) {
            products = productService.findAllByCategoryId(categoryId);
        } else {
            products = productService.findAll();
        }

        List<CategoryDto> categories = categoryService.findAll();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("searchText", searchText);
        model.addAttribute("selectedCategoryId", categoryId);

        return "home/index";
    }
}
