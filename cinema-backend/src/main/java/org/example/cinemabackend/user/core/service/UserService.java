package org.example.cinemabackend.user.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.user.application.dto.request.CreateCinemaManagerRequest;
import org.example.cinemabackend.user.application.dto.request.UpdateCinemaManagerRequest;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.example.cinemabackend.user.core.port.primary.UserMapper;
import org.example.cinemabackend.user.core.port.primary.UserUseCases;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<UserResponse> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::mapUserToUserResponse);
    }

    @Override
    public UserResponse getUser(String email) {
        throw new IllegalArgumentException("Not implemented");
    }

    @Override
    public CinemaManagerResponse getCinemaManager(String email) {
        final var cinemaManager = userRepository.findCinemaManagerByEmail(email).orElseThrow();
        return userMapper.mapUserToCinemaManagerResponse(cinemaManager);
    }

    @Override
    public void createCinemaManager(CreateCinemaManagerRequest createCinemaManagerRequest) {
        validateEmailIsNotTaken(createCinemaManagerRequest.email());
        final var cinemaManager = userMapper.mapCreateCinemaManagerRequestToUser(createCinemaManagerRequest);
        userRepository.save(cinemaManager);
    }

    @Override
    public void updateCinemaManager(String email, UpdateCinemaManagerRequest updateCinemaManagerRequest) {
        validateEmailIsNotTakenWhenUpdatingManager(email, updateCinemaManagerRequest.email());
        validateCinemaHasNoManager(updateCinemaManagerRequest);
        final var cinemaManagerToUpdate = userRepository.findCinemaManagerByEmail(email).orElseThrow();
        userMapper.mapUpdateCinemaManagerRequestToUser(cinemaManagerToUpdate, updateCinemaManagerRequest);
    }

    @Override
    public void deleteCinemaManager(String email) {

    }

    private void validateEmailIsNotTaken(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("User with email " + email + " already exists!");
        }
    }

    private void validateCinemaHasNoManager(UpdateCinemaManagerRequest updateCinemaManagerRequest) {
        if (updateCinemaManagerRequest.managedCinemaName() == null) {
            return;
        }
        final var cinema = cinemaRepository.findByName(updateCinemaManagerRequest.managedCinemaName()).orElseThrow();
        if (cinema.getCinemaManager() != null && !cinema.getCinemaManager().getEmail().equals(updateCinemaManagerRequest.email())) {
            throw new IllegalArgumentException("Cinema already has a manager");
        }
    }


    private void validateEmailIsNotTakenWhenUpdatingManager(String email, String updateEmail) {
        if (!email.equals(updateEmail) && userRepository.existsByEmail(updateEmail)) {
            throw new IllegalArgumentException("Email is already taken");
        }
    }
}
