package com.example.minicatalogproject.dtos.user;

import com.example.minicatalogproject.entities.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    @NotBlank(message = "Ju lutem shkruani emrin e perdoruesit")
    @Size(min = 3, max = 50, message = "Emri i perdoruesit duhet te jete 3 deri 50 karaktere")
    private String username;

    @Size(max = 100, message = "Fjalekalimi duhet te jete deri 100 karaktere")
    private String password;

    @NotNull(message = "Ju lutem zgjidhni rolin")
    private Role role;
}
