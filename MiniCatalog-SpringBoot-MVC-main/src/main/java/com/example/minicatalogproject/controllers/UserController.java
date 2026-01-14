package com.example.minicatalogproject.controllers;

import com.example.minicatalogproject.dtos.user.CreateUserRequest;
import com.example.minicatalogproject.dtos.user.UpdateUserRequest;
import com.example.minicatalogproject.dtos.user.UserDto;
import com.example.minicatalogproject.entities.Role;
import com.example.minicatalogproject.mappers.UserMapper;
import com.example.minicatalogproject.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public String index(Model model) {
        List<UserDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new CreateUserRequest());
        model.addAttribute("roles", Role.values());
        return "user/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("user") CreateUserRequest user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "user/add";
        }

        userService.create(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model) {
        UserDto user = userService.findById(id);
        if (user == null) {
            return "redirect:/admin/users";
        }

        UpdateUserRequest updateUserRequest = userMapper.toUpdateRequest(user);
        model.addAttribute("user", updateUserRequest);
        model.addAttribute("userId", id);
        model.addAttribute("roles", Role.values());
        return "user/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id, @Valid @ModelAttribute("user") UpdateUserRequest user, BindingResult bindingResult, Model model) {
        if (user.getPassword() != null && !user.getPassword().isBlank() && user.getPassword().length() < 6) {
            bindingResult.rejectValue("password", "error.user", "Fjalekalimi duhet te jete te pakten 6 karaktere");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("userId", id);
            model.addAttribute("roles", Role.values());
            return "user/update";
        }

        UserDto userFromDb = userService.findById(id);
        if (userFromDb == null) {
            return "redirect:/admin/users";
        }

        userService.update(id, user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        UserDto user = userService.findById(id);
        if (user == null) {
            return "redirect:/admin/users";
        }

        model.addAttribute("userToDelete", user);
        return "user/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteConfirm(@PathVariable int id) {
        UserDto user = userService.findById(id);
        if (user == null) {
            return "redirect:/admin/users";
        }

        userService.delete(id);
        return "redirect:/admin/users";
    }
}
