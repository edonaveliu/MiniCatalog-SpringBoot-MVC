package com.example.minicatalogproject.mappers;

import com.example.minicatalogproject.dtos.user.CreateUserRequest;
import com.example.minicatalogproject.dtos.user.UpdateUserRequest;
import com.example.minicatalogproject.dtos.user.UserDto;
import com.example.minicatalogproject.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-04T01:35:49+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.18 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setRole( user.getRole() );

        return userDto;
    }

    @Override
    public User toEntity(CreateUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( request.getUsername() );
        user.password( request.getPassword() );
        user.role( request.getRole() );

        return user.build();
    }

    @Override
    public UpdateUserRequest toUpdateRequest(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();

        updateUserRequest.setUsername( dto.getUsername() );
        updateUserRequest.setRole( dto.getRole() );

        return updateUserRequest;
    }

    @Override
    public void updateEntityFromDto(UpdateUserRequest request, User user) {
        if ( request == null ) {
            return;
        }

        user.setUsername( request.getUsername() );
        user.setRole( request.getRole() );
    }
}
