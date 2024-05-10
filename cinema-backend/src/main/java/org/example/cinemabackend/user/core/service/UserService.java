package org.example.cinemabackend.user.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.application.dto.response.UserTableResponse;
import org.example.cinemabackend.user.core.port.primary.UserMapper;
import org.example.cinemabackend.user.core.port.primary.UserUseCases;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class UserService implements UserUseCases {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserTableResponse> getUsers() {
        final var cinemaManagers = userRepository.findAllCinemaManagers();
        return cinemaManagers.stream().map(userMapper::mapUserToUserTableResponse).toList();
    }
}
