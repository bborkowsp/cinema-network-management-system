package org.example.cinemabackend.cinema.core.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;
import org.example.cinemabackend.cinema.core.port.primary.CinemaMapper;
import org.example.cinemabackend.cinema.core.port.primary.CinemaUseCases;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class CinemaService implements CinemaUseCases {

    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CinemaResponse> getCinemas() {
        final var cinemas = cinemaRepository.findAll();
        return cinemas.stream().map(cinemaMapper::mapCinemaToCinemaResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CinemaResponse getCinema(String name) {
        final var cinema = cinemaRepository.findByName(name).orElseThrow();
        return cinemaMapper.mapCinemaToCinemaResponse(cinema);
    }

    @Override
    @Transactional
    public void createCinema(CreateCinemaRequest createCinemaRequest) {
        validateCinemaDoesntExist(createCinemaRequest.name());
        final var cinema = cinemaMapper.mapCreateCinemaRequestToCinema(createCinemaRequest);
        cinemaRepository.save(cinema);
    }

    private void validateCinemaDoesntExist(String name) {
        if (cinemaRepository.existsByName(name)) {
            throw new IllegalArgumentException("Cinema with name " + name + " already exists.");
        }
    }
}
