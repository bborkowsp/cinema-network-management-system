package org.example.cinemabackend.user.core.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.user.application.dto.request.CreateCinemaManagerRequest;
import org.example.cinemabackend.user.application.dto.request.CreateUserRequest;
import org.example.cinemabackend.user.application.dto.request.UpdateCinemaManagerRequest;
import org.example.cinemabackend.user.application.dto.request.UpdateUserRequest;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.example.cinemabackend.user.core.domain.Role;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.primary.UserMapper;
import org.example.cinemabackend.user.core.port.primary.UserUseCases;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UserService implements UserUseCases {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private static final String USER_ALREADY_EXISTS_ERROR_MESSAGE = "User already exists";
    private final UserRepository userRepository;
    private final CinemaRepository cinemaRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

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
        final var user = userRepository.findByEmail(email).orElseThrow();
        return userMapper.mapUserToUserResponse(user);
    }

    @Override
    public CinemaManagerResponse getCinemaManager(String email) {
        final var cinemaManager = userRepository.findCinemaManagerByEmail(email).orElseThrow();
        return userMapper.mapUserToCinemaManagerResponse(cinemaManager);
    }

    @Override
    public void checkIfUserExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalStateException(USER_ALREADY_EXISTS_ERROR_MESSAGE);
        }
    }

    @Override
    public void createCinemaManager(CreateCinemaManagerRequest createCinemaManagerRequest) {
        validateUserDoesNotExist(createCinemaManagerRequest.email());
        validateCinemaHasNoManager(createCinemaManagerRequest.managedCinemaName(), createCinemaManagerRequest.email());
        final var cinemaManager = userMapper.mapCreateCinemaManagerRequestToUser(createCinemaManagerRequest);
        if (createCinemaManagerRequest.managedCinemaName() != null) {
            final var cinema = cinemaRepository.findByName(createCinemaManagerRequest.managedCinemaName()).orElseThrow();
            cinema.setCinemaManager(cinemaManager);
            cinemaRepository.save(cinema);
        } else {
            userRepository.save(cinemaManager);
        }
    }

    @Override
    public void createUser(CreateUserRequest createUserRequest) {
        validateUserDoesNotExist(createUserRequest.email());
        final var user = userMapper.mapCreateUserRequestToUser(createUserRequest);
        LOGGER.info("User " + user.getEmail() + " with role " + user.getRole() + " created");
        userRepository.save(user);
    }

    @Override
    public void updateCinemaManager(String email, UpdateCinemaManagerRequest updateCinemaManagerRequest) {
        validateEmailIsNotTaken(email, updateCinemaManagerRequest.email());
        validateCinemaHasNoManager(updateCinemaManagerRequest.managedCinemaName(), updateCinemaManagerRequest.email());
        final var cinemaManagerToUpdate = userRepository.findCinemaManagerByEmail(email).orElseThrow();
        userMapper.mapUpdateCinemaManagerRequestToUser(cinemaManagerToUpdate, updateCinemaManagerRequest);
        userRepository.save(cinemaManagerToUpdate);
    }

    @Override
    public void updateCinemaNetworkManager(String email, UpdateUserRequest updateCinemaManagerRequest) {
        validateEmailIsNotTaken(email, updateCinemaManagerRequest.email());
        final var cinemaNetworkManagerToUpdate = userRepository.findByEmail(email).orElseThrow();
        checkIfCurrentPasswordIsEmpty(cinemaNetworkManagerToUpdate, updateCinemaManagerRequest);
        userMapper.mapUpdateCinemaNetworkManagerRequestToUser(cinemaNetworkManagerToUpdate, updateCinemaManagerRequest);
        userRepository.save(cinemaNetworkManagerToUpdate);
    }

    @Override
    public void deleteCinemaManager(String email) {
        final var user = userRepository.findCinemaManagerByEmail(email).orElseThrow();
        validateUserIsNotAssignedToCinema(user);
        userRepository.deleteUser(user);
    }

    @Override
    public void deleteUser(String email) {
        final var user = userRepository.findByEmail(email).orElseThrow();
        validateUserIsNotAssignedToCinema(user);
        validateUserIsNotAdmin(user);
        userRepository.deleteUser(user);
    }

    private void validateUserIsNotAdmin(User user) {
        if (user.getRole().equals(Role.ADMIN)) {
            throw new IllegalArgumentException("Cannot delete admin user");
        }
    }

    private void validateUserIsNotAssignedToCinema(User user) {
        if (cinemaRepository.existsByCinemaManagerEmail(user.getEmail())) {
            throw new IllegalArgumentException("User is assigned to a cinema");
        }
    }

    private void checkIfCurrentPasswordIsEmpty(User cinemaNetworkManagerToUpdate, UpdateUserRequest updateUserRequest) {
        final var currentPassword = updateUserRequest.currentPassword();
        if (!currentPassword.isEmpty()) {
            validateCurrentPasswordIsCorrect(cinemaNetworkManagerToUpdate, currentPassword);
            validateNewPasswordIsNotEmpty(updateUserRequest.newPassword());
        }
    }

    private void validateCurrentPasswordIsCorrect(User cinemaNetworkManagerToUpdate, String currentPassword) {
        if (!passwordEncoder.matches(currentPassword, cinemaNetworkManagerToUpdate.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }
    }

    private void validateNewPasswordIsNotEmpty(String newPassword) {
        if (newPassword.isEmpty()) {
            throw new IllegalArgumentException("New password cannot be empty");
        }
    }

    private void validateEmailIsNotTaken(String email, String oldEmail) {
        if (!email.equals(oldEmail) && userRepository.existsByEmail(oldEmail)) {
            throw new IllegalStateException("Email is already taken");
        }
    }

    private void validateCinemaHasNoManager(String managedCinemaName, String email) {
        if (managedCinemaName == null || managedCinemaName.isEmpty()) {
            return;
        }
        final var cinema = cinemaRepository.findByName(managedCinemaName).orElseThrow();
        if (cinema.getCinemaManager() != null && !cinema.getCinemaManager().getEmail().equals(email)) {
            throw new IllegalStateException("Cinema already has a manager");
        }
    }

    private void validateUserDoesNotExist(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalStateException("User with email " + email + " already exists!");
        }
    }
}
