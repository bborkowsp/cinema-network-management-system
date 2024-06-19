package org.example.cinemabackend.user.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.port.primary.CinemaMapper;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.infrastructure.adapter.secondary.CinemaJpaRepository;
import org.example.cinemabackend.user.application.dto.request.CreateCinemaManagerRequest;
import org.example.cinemabackend.user.application.dto.request.UpdateCinemaManagerRequest;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.example.cinemabackend.user.application.dto.response.CinemaNetworkManagerResponse;
import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.example.cinemabackend.user.core.domain.Role;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.primary.UserMapper;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
class UserMapperService implements UserMapper {

    @Lazy
    private final CinemaMapper cinemaMapper;
    private final CinemaRepository cinemaRepository;
    private final UserRepository userRepository;
    private final CinemaJpaRepository cinemaJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse mapUserToUserResponse(User cinemaManager) {
        return UserResponse.builder()
                .firstName(cinemaManager.getFirstName())
                .lastName(cinemaManager.getLastName())
                .email(cinemaManager.getEmail())
                .role(cinemaManager.getRole())
                .build();
    }

    @Override
    public CinemaManagerTableResponse mapUserToCinemaManagerTableResponse(User user) {
        final var managedCinema = findByCinemaManager(user);

        return CinemaManagerTableResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .managedCinema(managedCinema == null ? null : cinemaMapper.mapCinemaToCinemaResponse(managedCinema))
                .build();
    }

    @Override
    public CinemaManagerResponse mapUserToCinemaManagerResponse(User user) {
        final var managedCinema = findByCinemaManager(user);

        return CinemaManagerResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .managedCinemaName(managedCinema == null ? null : cinemaMapper.mapCinemaToCinemaResponse(managedCinema).name())
                .role(user.getRole())
                .build();
    }

    @Override
    @Transactional
    public void mapUpdateCinemaManagerRequestToUser(User cinemaManagerToUpdate, UpdateCinemaManagerRequest updateCinemaManagerRequest) {
        cinemaManagerToUpdate.setFirstName(updateCinemaManagerRequest.firstName());
        cinemaManagerToUpdate.setLastName(updateCinemaManagerRequest.lastName());

        if (updateCinemaManagerRequest.managedCinemaName() != null) {
            updateCinemaManagerIfUpdatedManagedCinemaIsNotNull(cinemaManagerToUpdate, updateCinemaManagerRequest);
        } else {
            updateCinemaManagerIfUpdatedManagedCinemaIsNull(cinemaManagerToUpdate, updateCinemaManagerRequest);
        }
    }

    @Override
    public User mapCreateCinemaManagerRequestToUser(CreateCinemaManagerRequest createCinemaManagerRequest) {
        final var passwordHash = passwordEncoder.encode(createCinemaManagerRequest.password());
        return new User(
                createCinemaManagerRequest.firstName(),
                createCinemaManagerRequest.lastName(),
                createCinemaManagerRequest.email(),
                passwordHash,
                Role.CINEMA_MANAGER
        );
    }

    @Override
    public CinemaNetworkManagerResponse mapUserToCinemaNetworkManagerResponse(User cinemaNetworkManager) {
        return CinemaNetworkManagerResponse.builder()
                .firstName(cinemaNetworkManager.getFirstName())
                .lastName(cinemaNetworkManager.getLastName())
                .email(cinemaNetworkManager.getEmail())
                .role(cinemaNetworkManager.getRole())
                .build();
    }

    private void updateCinemaManagerIfUpdatedManagedCinemaIsNull(User cinemaManagerToUpdate, UpdateCinemaManagerRequest updateCinemaManagerRequest) {
        final var oldManagedCinema = getCinemaByUserEmail(cinemaManagerToUpdate.getEmail());

        removeCinemaManagerFromOldManagedCinema(oldManagedCinema);
        cinemaManagerToUpdate.setEmail(updateCinemaManagerRequest.email());
        userRepository.save(cinemaManagerToUpdate);
    }

    private void updateCinemaManagerIfUpdatedManagedCinemaIsNotNull(User cinemaManagerToUpdate, UpdateCinemaManagerRequest updateCinemaManagerRequest) {
        final var newManagedCinema = cinemaRepository.findByName(updateCinemaManagerRequest.managedCinemaName()).orElseThrow();
        final var oldManagedCinema = getCinemaByUserEmail(cinemaManagerToUpdate.getEmail());

        removeCinemaManagerFromOldManagedCinema(oldManagedCinema);

        cinemaManagerToUpdate.setEmail(updateCinemaManagerRequest.email());
        userRepository.save(cinemaManagerToUpdate);
        cinemaRepository.updateCinemaManager(newManagedCinema, cinemaManagerToUpdate.getId());
    }

    private void removeCinemaManagerFromOldManagedCinema(Cinema oldManagedCinema) {
        if (oldManagedCinema != null) {
            cinemaJpaRepository.updateCinemaManagerToNull(oldManagedCinema.getId());
        }
    }

    private Cinema getCinemaByUserEmail(String email) {
        return cinemaRepository.findByUserEmail(email).orElse(null);
    }

    private Cinema findByCinemaManager(User user) {
        return cinemaRepository.findByCinemaManager(user).orElse(null);
    }

}
