package org.example.cinemabackend.cinema.core.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;
import org.example.cinemabackend.cinema.application.dto.response.CinemaTableResponse;
import org.example.cinemabackend.cinema.core.port.primary.CinemaMapper;
import org.example.cinemabackend.cinema.core.port.primary.CinemaUseCases;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class CinemaService implements CinemaUseCases {

    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;

    @Override
    public Page<CinemaTableResponse> getCinemas(Pageable pageable) {
        return cinemaRepository.findAll(pageable).map(cinemaMapper::mapCinemaToCinemaTableRow);
    }

    @Override
    public List<CinemaTableResponse> getCinemas() {
        return cinemaRepository.findAll().stream().map(cinemaMapper::mapCinemaToCinemaTableRow).toList();
    }

    @Override
    public CinemaResponse getCinema(String name) {
        final var cinema = cinemaRepository.findByName(name).orElseThrow();
        return cinemaMapper.mapCinemaToCinemaResponse(cinema);
    }

    @Override
    public void createCinema(CreateCinemaRequest createCinemaRequest) {
        validateCinemaDoesntExist(createCinemaRequest.name());
        final var cinema = cinemaMapper.mapCreateCinemaRequestToCinema(createCinemaRequest);
        cinemaRepository.save(cinema);
    }

    @Override
    @Transactional
    public void deleteCinema(String name) {
        validateCinemaExists(name);
        cinemaRepository.deleteByName(name);
    }

    private void validateCinemaExists(String name) {
        if (cinemaRepository.findByName(name).isEmpty()) {
            throw new IllegalArgumentException("Cinema with name " + name + " doesn't exist.");
        }
    }

    private void validateCinemaDoesntExist(String name) {
        if (cinemaRepository.existsByName(name)) {
            throw new IllegalArgumentException("Cinema with name " + name + " already exists.");
        }
    }
}
