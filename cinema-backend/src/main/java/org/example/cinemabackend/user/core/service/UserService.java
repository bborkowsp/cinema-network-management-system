package org.example.cinemabackend.user.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.example.cinemabackend.user.core.port.primary.UserMapper;
import org.example.cinemabackend.user.core.port.primary.UserUseCases;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
class UserService implements UserUseCases {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<CinemaManagerTableResponse> getCinemaManagers(Pageable pageable) {
        return userRepository.findAllCinemaManagers(pageable).map(userMapper::mapUserToCinemaManagerTableResponse);
    }

    @Override
    public List<CinemaManagerResponse> getCinemaManagers() {
        final var cinemaManagers = userRepository.findAllCinemaManagers();
        return cinemaManagers.stream().map(userMapper::mapUserToCinemaManagerResponse).toList();
    }
}
