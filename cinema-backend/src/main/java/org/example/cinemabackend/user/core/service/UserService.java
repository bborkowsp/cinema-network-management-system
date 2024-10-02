package org.example.cinemabackend.user.core.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.cinemabackend.auth.core.port.primary.AuthUseCases;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.user.application.dto.request.*;
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

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
class UserService implements UserUseCases {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final CinemaRepository cinemaRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthUseCases authUseCases;

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
    public UserResponse getCustomerProfile() {
        final var email = authUseCases.getCurrentUserEmail();
        final var user = userRepository.findByEmail(email).orElseThrow();
        return userMapper.mapUserToUserResponse(user);
    }

    @Override
    public void createCinemaManager(CreateCinemaManagerRequest createCinemaManagerRequest) {
        validateUserDoesNotExist(createCinemaManagerRequest.email());
        validateCinemaHasNoManager(createCinemaManagerRequest.managedCinemaName(), createCinemaManagerRequest.email());
        final var cinemaManager = userMapper.mapCreateCinemaManagerRequestToUser(createCinemaManagerRequest);
        final var cinema = cinemaRepository.findByName(createCinemaManagerRequest.managedCinemaName()).orElseThrow();
        cinema.setCinemaManager(cinemaManager);
        cinemaRepository.save(cinema);
    }

    @Override
    public void createUser(CreateUserRequest createUserRequest) {
        validateUserDoesNotExist(createUserRequest.email());
        final var user = userMapper.mapCreateUserRequestToUser(createUserRequest);
        user.setCreatedAt(LocalDateTime.now());
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
    public void updateCustomerProfile(UpdateCustomerProfileRequest updateCustomerProfileRequest) {
        final var email = authUseCases.getCurrentUserEmail();
        final var userToUpdate = userRepository.findByEmail(email).orElseThrow();
        updateUserFields(userToUpdate, updateCustomerProfileRequest);
        userRepository.save(userToUpdate);
    }

    private void updateUserFields(User userToUpdate, UpdateCustomerProfileRequest updateCustomerProfileRequest) {
        userToUpdate.setFirstName(updateCustomerProfileRequest.firstName());
        userToUpdate.setLastName(updateCustomerProfileRequest.lastName());
        if (!updateCustomerProfileRequest.email().equals(userToUpdate.getEmail())) {
            validateUserDoesNotExist(updateCustomerProfileRequest.email());
            userToUpdate.setEmail(updateCustomerProfileRequest.email());
        }
    }

    @Override
    public void updatePassword(UpdatePasswordRequest updateCustomerProfileRequest) {
        final var email = authUseCases.getCurrentUserEmail();
        final var userToUpdate = userRepository.findByEmail(email).orElseThrow();
        validateCurrentPasswordIsCorrect(userToUpdate, updateCustomerProfileRequest.currentPassword());
        userToUpdate.setPassword(passwordEncoder.encode(updateCustomerProfileRequest.newPassword()));
        userRepository.save(userToUpdate);
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
        if (managedCinemaName == null) {
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
