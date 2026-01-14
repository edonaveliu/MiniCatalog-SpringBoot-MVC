package com.example.minicatalogproject.services.impls;

import com.example.minicatalogproject.dtos.user.CreateUserRequest;
import com.example.minicatalogproject.dtos.user.UpdateUserRequest;
import com.example.minicatalogproject.dtos.user.UserDto;
import com.example.minicatalogproject.entities.User;
import com.example.minicatalogproject.mappers.UserMapper;
import com.example.minicatalogproject.repositories.UserRepository;
import com.example.minicatalogproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElse(null);
    }

    @Override
    public UserDto create(CreateUserRequest request) {
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto update(Integer id, UpdateUserRequest request) {
        return userRepository.findById(id)
                .map(user -> {
                    userMapper.updateEntityFromDto(request, user);
                    if (request.getPassword() != null && !request.getPassword().isBlank()) {
                        user.setPassword(passwordEncoder.encode(request.getPassword()));
                    }
                    User updatedUser = userRepository.save(user);
                    return userMapper.toDto(updatedUser);
                })
                .orElse(null);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}

