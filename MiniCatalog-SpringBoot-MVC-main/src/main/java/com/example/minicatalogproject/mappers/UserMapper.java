package com.example.minicatalogproject.mappers;

import com.example.minicatalogproject.dtos.user.CreateUserRequest;
import com.example.minicatalogproject.dtos.user.UpdateUserRequest;
import com.example.minicatalogproject.dtos.user.UserDto;
import com.example.minicatalogproject.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(CreateUserRequest request);

    UpdateUserRequest toUpdateRequest(UserDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateEntityFromDto(UpdateUserRequest request, @MappingTarget User user);
}
