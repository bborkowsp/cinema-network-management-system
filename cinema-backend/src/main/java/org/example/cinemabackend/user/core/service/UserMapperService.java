package org.example.cinemabackend.user.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.port.primary.CinemaMapper;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.primary.UserMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
class UserMapperService implements UserMapper {

    @Lazy
    private final CinemaMapper cinemaMapper;

    private final CinemaRepository cinemaRepository;

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
        return CinemaManagerTableResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .managedCinema(cinemaMapper.mapCinemaToCinemaResponse(cinemaRepository.findByCinemaManager(user)))
                .build();
    }
}
