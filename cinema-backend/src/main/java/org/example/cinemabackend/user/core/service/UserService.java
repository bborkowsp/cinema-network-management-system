package org.example.cinemabackend.user.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.example.cinemabackend.user.application.dto.response.UpdateCinemaManagerRequest;
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
    private final CinemaRepository cinemaRepository;
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

    @Override
    public CinemaManagerResponse getCinemaManager(String email) {
        final var cinemaManager = userRepository.findCinemaManagerByEmail(email).orElseThrow();
        return userMapper.mapUserToCinemaManagerResponse(cinemaManager);
    }

    @Override
    public void updateCinemaManager(String email, UpdateCinemaManagerRequest updateCinemaManagerRequest) {
        validateEmailIsNotTaken(email, updateCinemaManagerRequest.email());
        validateCinemaHasNoManager(updateCinemaManagerRequest);
        final var cinemaManager = userRepository.findCinemaManagerByEmail(email).orElseThrow();
        userMapper.updateCinemaManager(cinemaManager, updateCinemaManagerRequest);
    }

    private void validateCinemaHasNoManager(UpdateCinemaManagerRequest updateCinemaManagerRequest) {
        final var cinema = cinemaRepository.findByName(updateCinemaManagerRequest.managedCinemaName()).orElseThrow();
        if (cinema.getCinemaManager() != null && !cinema.getCinemaManager().getEmail().equals(updateCinemaManagerRequest.email())) {
            throw new IllegalArgumentException("Cinema already has a manager");
        }
    }


    private void validateEmailIsNotTaken(String email, String updateEmail) {
        if (!email.equals(updateEmail) && userRepository.existsByEmail(updateEmail)) {
            throw new IllegalArgumentException("Email is already taken");
        }
    }
}
