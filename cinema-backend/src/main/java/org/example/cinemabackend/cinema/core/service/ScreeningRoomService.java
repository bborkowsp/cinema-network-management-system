package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.cinemabackend.auth.core.port.primary.AuthUseCases;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningRoomUseCases;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ScreeningRoomService implements ScreeningRoomUseCases {
    private static final Logger LOGGER = LogManager.getLogger(CinemaService.class);
    private final CinemaRepository cinemaRepository;
    private final AuthUseCases authUseCases;

    @Override
    public List<String> getScreeningRoomsNames() {
        final var email = authUseCases.getCurrentUserEmail();
        final var cinema = getCinemaByUserEmail(email);
        LOGGER.info("User with email " + email + " requested all screening rooms.");
        return cinema.getScreeningRooms().stream().map(ScreeningRoom::getName).toList();
    }

    private Cinema getCinemaByUserEmail(String email) {
        return cinemaRepository.findByUserEmail(email).orElseThrow();
    }
}
