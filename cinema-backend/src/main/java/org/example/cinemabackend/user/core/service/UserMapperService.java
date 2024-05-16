package org.example.cinemabackend.user.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.port.primary.CinemaMapper;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.example.cinemabackend.user.application.dto.response.UpdateCinemaManagerRequest;
import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.primary.UserMapper;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
class UserMapperService implements UserMapper {

    @Lazy
    private final CinemaMapper cinemaMapper;
    private final CinemaRepository cinemaRepository;
    private final UserRepository userRepository;

    @Override
    public UserResponse mapUserToUserResponse(User cinemaManager) {
        return UserResponse.builder()
                .firstName(cinemaManager.getFirstName())
                .lastName(cinemaManager.getLastName())
                .email(cinemaManager.getEmail())
                .build();
    }

    @Override
    public CinemaManagerTableResponse mapUserToCinemaManagerTableResponse(User user) {
        final var managedCinema = cinemaRepository.findByCinemaManager(user);

        return CinemaManagerTableResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .managedCinema(managedCinema == null ? null : cinemaMapper.mapCinemaToCinemaResponse(managedCinema))
                .build();
    }

    @Override
    public CinemaManagerResponse mapUserToCinemaManagerResponse(User user) {
        final var managedCinema = cinemaRepository.findByCinemaManager(user);

        return CinemaManagerResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .managedCinemaName(managedCinema == null ? null : cinemaMapper.mapCinemaToCinemaResponse(managedCinema).name())
                .build();
    }

    @Override
    @Transactional
    public void updateCinemaManager(User cinemaManager, UpdateCinemaManagerRequest updateCinemaManagerRequest) {
        final var oldManagedCinema = cinemaRepository.findByCinemaManager(cinemaManager);
        if (oldManagedCinema != null) {
            oldManagedCinema.setCinemaManager(null);
            cinemaRepository.save(oldManagedCinema);
        }

        cinemaManager.setFirstName(updateCinemaManagerRequest.firstName());
        cinemaManager.setLastName(updateCinemaManagerRequest.lastName());
        cinemaManager.setEmail(updateCinemaManagerRequest.email());

        final var managedCinema = cinemaRepository.findByName(updateCinemaManagerRequest.managedCinemaName()).orElseThrow();
        managedCinema.setCinemaManager(cinemaManager);
        cinemaRepository.save(managedCinema);
    }

}
