package com.example.minicatalogproject.services;

import com.example.minicatalogproject.dtos.user.CreateUserRequest;
import com.example.minicatalogproject.dtos.user.UpdateUserRequest;
import com.example.minicatalogproject.dtos.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(Integer id);
    UserDto create(CreateUserRequest request);
    UserDto update(Integer id, UpdateUserRequest request);
    void delete(Integer id);
    boolean existsByUsername(String username);
}
