package com.example.minicatalogproject.dtos.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {
    @NotNull(message = "Ju lutem shkruani emrin e kategorise")
    @Size(min = 2, max = 50, message = "Emri i kategorise duhet te jete 2 - 50 karaktere")
    private String name;

    @Size(min = 10, max = 255, message = "Pershkruarja e kategorise duhet te jete 10 - 255 karaktere")
    private String description;
}
