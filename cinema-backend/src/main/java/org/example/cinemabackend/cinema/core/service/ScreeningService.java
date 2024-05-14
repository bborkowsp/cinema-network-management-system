package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningMapper;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningUseCases;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ScreeningService implements ScreeningUseCases {

    private final ScreeningRepository screeningRepository;
    private final CinemaRepository cinemaRepository;
    private final ScreeningMapper screeningMapper;

    @Override
    public List<ScreeningResponse> getScreenings() {
        return screeningRepository.findAll().stream().map(screeningMapper::mapScreeningToScreeningResponse).toList();
    }

    @Override
    public List<ScreeningResponse> getScreenings(String email) {
        final var cinema = cinemaRepository.findByUserEmail(email);
        return cinema.getRepertory().stream().map(screeningMapper::mapScreeningToScreeningResponse).toList();
    }
}
