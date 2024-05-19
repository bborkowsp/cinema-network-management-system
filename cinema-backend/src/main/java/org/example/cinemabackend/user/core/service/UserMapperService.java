package org.example.cinemabackend.user.core.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Cinema;
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

    @PersistenceContext
    private EntityManager entityManager;

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
    public void updateCinemaManager(User cinemaManagerToUpdate, UpdateCinemaManagerRequest updateCinemaManagerRequest) {
        cinemaManagerToUpdate.setFirstName(updateCinemaManagerRequest.firstName());
        cinemaManagerToUpdate.setLastName(updateCinemaManagerRequest.lastName());

        if (updateCinemaManagerRequest.managedCinemaName() != null) {
            updateCinemaManagerIfUpdatedManagedCinemaIsNotNull(cinemaManagerToUpdate, updateCinemaManagerRequest);
        } else {
            updateCinemaManagerIfUpdatedManagedCinemaIsNull(cinemaManagerToUpdate, updateCinemaManagerRequest);
        }
    }

    private void updateCinemaManagerIfUpdatedManagedCinemaIsNull(User cinemaManagerToUpdate, UpdateCinemaManagerRequest updateCinemaManagerRequest) {
        final var oldManagedCinema = cinemaRepository.findByUserEmail(cinemaManagerToUpdate.getEmail());

        removeCinemaManagerFromOldManagedCinema(oldManagedCinema);
        cinemaManagerToUpdate.setEmail(updateCinemaManagerRequest.email());
        userRepository.save(cinemaManagerToUpdate);
    }

    private void updateCinemaManagerIfUpdatedManagedCinemaIsNotNull(User cinemaManagerToUpdate, UpdateCinemaManagerRequest updateCinemaManagerRequest) {
        final var newManagedCinema = cinemaRepository.findByName(updateCinemaManagerRequest.managedCinemaName()).orElseThrow();
        final var oldManagedCinema = cinemaRepository.findByUserEmail(cinemaManagerToUpdate.getEmail());

        removeCinemaManagerFromOldManagedCinema(oldManagedCinema);

        cinemaManagerToUpdate.setEmail(updateCinemaManagerRequest.email());
        newManagedCinema.setCinemaManager(cinemaManagerToUpdate);
        cinemaRepository.save(newManagedCinema);
    }

    private void removeCinemaManagerFromOldManagedCinema(Cinema oldManagedCinema) {
        if (oldManagedCinema != null) {
            oldManagedCinema.setCinemaManager(null);
            cinemaRepository.save(oldManagedCinema);
            entityManager.flush();
        }
    }

}
