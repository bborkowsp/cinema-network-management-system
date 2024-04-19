package org.example.cinemabackend.user.core.service;

import org.example.cinemabackend.user.application.dto.UserResponse;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.primary.UserMapper;
import org.springframework.stereotype.Service;

@Service
class UserMapperService implements UserMapper {
    @Override
    public UserResponse mapUserToUserResponse(User cinemaManager) {
        return UserResponse.builder()
                .firstName(cinemaManager.getFirstName())
                .lastName(cinemaManager.getLastName())
                .email(cinemaManager.getEmail())
                .build();
    }
}
