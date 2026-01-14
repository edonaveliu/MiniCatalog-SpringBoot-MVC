package com.example.minicatalogproject.configs;

import com.example.minicatalogproject.entities.Category;
import com.example.minicatalogproject.entities.Product;
import com.example.minicatalogproject.entities.Role;
import com.example.minicatalogproject.entities.User;
import com.example.minicatalogproject.repositories.CategoryRepository;
import com.example.minicatalogproject.repositories.ProductRepository;
import com.example.minicatalogproject.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initData(UserRepository userRepository,
                                      CategoryRepository categoryRepository,
                                      ProductRepository productRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User user = User.builder()
                        .username("user")
                        .password(passwordEncoder.encode("user123"))
                        .role(Role.USER)
                        .build();

                User admin = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .role(Role.ADMIN)
                        .build();

                userRepository.saveAll(List.of(user, admin));
            }

            if (categoryRepository.count() == 0) {
                Category elektronike = Category.builder()
                        .name("Elektronikë")
                        .description("Pajisje elektronike dhe vegla")
                        .build();

                Category veshje = Category.builder()
                        .name("Veshje")
                        .description("Rroba dhe artikuj mode")
                        .build();

                Category libra = Category.builder()
                        .name("Libra")
                        .description("Libra dhe literaturë")
                        .build();

                categoryRepository.saveAll(List.of(elektronike, veshje, libra));

                Product laptop = Product.builder()
                        .name("Laptop")
                        .description("Laptop me performancë të lartë")
                        .price(999.99)
                        .stock(10)
                        .image("https://placehold.co/600x400.png")
                        .category(elektronike)
                        .build();

                Product telefon = Product.builder()
                        .name("Telefon")
                        .description("Modeli më i ri i telefonit")
                        .price(1399.99)
                        .stock(25)
                        .image("https://placehold.co/600x400.png")
                        .category(elektronike)
                        .build();

                Product bluze = Product.builder()
                        .name("Bluzë")
                        .description("Bluzë pambuku për çdo ditë")
                        .price(29.99)
                        .stock(50)
                        .image("https://placehold.co/600x400.png")
                        .category(veshje)
                        .build();

                Product xhinse = Product.builder()
                        .name("Xhinse")
                        .description("Xhinse klasike denim")
                        .price(59.99)
                        .stock(30)
                        .image("https://placehold.co/600x400.png")
                        .category(veshje)
                        .build();

                Product liber = Product.builder()
                        .name("Programim Java")
                        .description("Udhëzues i plotë për Java")
                        .price(49.99)
                        .stock(15)
                        .image("https://placehold.co/600x400.png")
                        .category(libra)
                        .build();

                productRepository.saveAll(List.of(laptop, telefon, bluze, xhinse, liber));
            }
        };
    }
}
