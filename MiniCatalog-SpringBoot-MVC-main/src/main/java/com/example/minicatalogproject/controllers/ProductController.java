package com.example.minicatalogproject.controllers;

import com.example.minicatalogproject.dtos.category.CategoryDto;
import com.example.minicatalogproject.dtos.product.CreateProductRequest;
import com.example.minicatalogproject.dtos.product.ProductDto;
import com.example.minicatalogproject.dtos.product.UpdateProductRequest;
import com.example.minicatalogproject.mappers.ProductMapper;
import com.example.minicatalogproject.services.CategoryService;
import com.example.minicatalogproject.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    @GetMapping
    public String index(@RequestParam(required = false) String searchText, Model model) {
        List<ProductDto> products = productService.findAll();
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new CreateProductRequest());
        List<CategoryDto> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "product/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("product") CreateProductRequest product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "product/add";
        }

        productService.create(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model) {
        ProductDto product = productService.findById(id);
        if (product == null) {
            return "redirect:/admin/products";
        }

        UpdateProductRequest updateProductRequest = productMapper.toUpdateRequest(product);
        List<CategoryDto> categories = categoryService.findAll();

        model.addAttribute("product", updateProductRequest);
        model.addAttribute("productId", id);
        model.addAttribute("categories", categories);
        return "product/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id, @Valid @ModelAttribute("product") UpdateProductRequest product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("productId", id);
            model.addAttribute("categories", categoryService.findAll());
            return "product/update";
        }
        ProductDto productFromDb = productService.findById(id);

        if (productFromDb == null) {
            return "redirect:/admin/products";
        }

        ProductDto updatedProduct = productService.update(id, product);
        if (updatedProduct == null)
            return "product/update";

        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        ProductDto product = productService.findById(id);
        if (product == null)
            return "redirect:/admin/products";

        model.addAttribute("productToDelete", product);
        return "product/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        ProductDto product = productService.findById(id);
        if (product == null)
            return "redirect:/admin/products";

        productService.delete(product.getId());
        return "redirect:/admin/products";
    }
}