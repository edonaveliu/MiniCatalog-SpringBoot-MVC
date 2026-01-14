package com.example.minicatalogproject.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PositivePriceValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PositivePrice {
    String message() default "Cmimi duhet te jete me i madh se 0";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

