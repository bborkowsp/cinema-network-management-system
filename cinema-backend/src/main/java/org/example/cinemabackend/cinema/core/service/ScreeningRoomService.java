package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningRoomUseCases;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ScreeningRoomService implements ScreeningRoomUseCases {
    private final CinemaRepository cinemaRepository;

    @Override
    public List<String> getScreeningRoomsNames(String email) {
        final var cinema = getCinemaByUserEmail(email);
        return cinema.getScreeningRooms().stream().map(ScreeningRoom::getName).toList();
    }

    private Cinema getCinemaByUserEmail(String email) {
        return cinemaRepository.findByUserEmail(email).orElseThrow();
    }
}
