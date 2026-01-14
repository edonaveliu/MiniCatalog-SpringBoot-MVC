package com.example.minicatalogproject.entities;

import com.example.minicatalogproject.validations.PositivePrice;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @PositivePrice(message = "Cmimi duhet te jete me i madh se 0")
    private Double price;

    @Column(nullable = false)
    @PositiveOrZero(message = "Stoku duhet te jete me i madh ose i barabarte me 0")
    private int stock;

    private String description;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
