package com.example.minicatalogproject.controllers;


import com.example.minicatalogproject.dtos.category.CategoryDto;
import com.example.minicatalogproject.dtos.category.CreateCategoryRequest;
import com.example.minicatalogproject.dtos.category.UpdateCategoryRequest;
import com.example.minicatalogproject.dtos.product.ProductDto;
import com.example.minicatalogproject.services.CategoryService;
import com.example.minicatalogproject.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        List<CategoryDto> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "category/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("category", new CreateCategoryRequest());
        return "category/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("category") CreateCategoryRequest category, BindingResult br) {
        if (br.hasErrors()) {
            return "category/add";
        }
        categoryService.create(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        CategoryDto categoryFromDb = categoryService.findById(id);
        if (categoryFromDb == null)
            return "redirect:/admin/categories";
        model.addAttribute("category", categoryFromDb);
        return "category/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute UpdateCategoryRequest category, BindingResult br) {
        if (br.hasErrors())
            return "category/update";
        CategoryDto categoryFromDb = categoryService.findById(id);

        if (categoryFromDb == null)
            return "redirect:/admin/categories";

        CategoryDto updatedCategory = categoryService.update(id, category);
        if (updatedCategory == null)
            return "category/update";

        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        CategoryDto category = categoryService.findById(id);
        if (category == null)
            return "redirect:/admin/categories";

        int productsCount = productService.countByCategoryId(category.getId());

        model.addAttribute("categoryToDelete", category);
        model.addAttribute("productsCount", productsCount);

        return "category/delete";
    }

    @PostMapping("/delete/{id}")
    @Transactional
    public String deleteCategory(@PathVariable Integer id, Model model) {
        CategoryDto category = categoryService.findById(id);

        if (category == null)
            return "redirect:/admin/categories";

        int productsCount = productService.countByCategoryId(category.getId());
        if (productsCount > 0) {
            model.addAttribute("categoryToDelete", category);
            model.addAttribute("productsCount", productsCount);
            model.addAttribute("errorMessage", "Ju nuk mund te fshini kete kategori sepse ka produkte");

            return "category/delete";
        }

        categoryService.delete(category.getId());
        return "redirect:/admin/categories";
    }

    @GetMapping("/{id}/products")
    public String products(@PathVariable int id, Model model) {
        CategoryDto category = categoryService.findById(id);
        if (category == null)
            return "redirect:/categories";

        List<ProductDto> products = productService.findAllByCategoryId(category.getId());

        model.addAttribute("category", category);
        model.addAttribute("products", products);

        return "category/products";
    }
}
