package com.example.minicatalogproject.dtos.product;

import com.example.minicatalogproject.validations.PositivePrice;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    @NotNull(message = "Ju lutem shkruani titullin")
    @Size(min = 2, max = 50, message = "Emri duhet te jete 2 deri 50 karaktere")
    private String name;

    @Size(min = 10, max = 255, message = "Pershkrimi duhet te jete 10 deri 255 karaktere")
    private String description;

    @NotNull(message = "Ju lutem shkruani cmimin")
    @PositivePrice(message = "Cmimi duhet te jete me i madh se 0")
    private Double price;

    @NotNull(message = "Ju lutem shkruani sasin")
    @PositiveOrZero(message = "Sasia duhet te jete me e madhe ose e barabart me 0")
    private int stock;

    @Size(min = 10, max = 255, message = "Linku i imazhit duhet te jete 10 deri 255 karaktere")
    private String image;

    @Positive(message = "Celsi i huaj duhet te jet pozitiv")
    private Integer categoryId;
}
