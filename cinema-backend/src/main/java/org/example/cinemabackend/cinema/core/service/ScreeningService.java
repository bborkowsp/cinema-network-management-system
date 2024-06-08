package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.ScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningMapper;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningUseCases;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRoomRepository;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
class ScreeningService implements ScreeningUseCases {
    private final ScreeningMapper screeningMapper;
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final ScreeningRepository screeningRepository;
    private final ScreeningRoomRepository screeningRoomRepository;

    @Override
    public List<ScreeningResponse> getScreenings() {
        return screeningRepository.findAll().stream().map(screeningMapper::mapScreeningToScreeningResponse).toList();
    }

    @Override
    public List<ScreeningResponse> getScreenings(String email) {
        final var cinema = cinemaRepository.findByUserEmail(email).orElse(null);
        if (cinema == null || cinema.getRepertory().isEmpty()) {
            return Collections.emptyList();
        }
        return cinema.getRepertory().stream().map(screeningMapper::mapScreeningToScreeningResponse).toList();
    }

    @Override
    public ScreeningResponse getScreening(Long id) {
        final var screening = getScreeningById(id);
        return screeningMapper.mapScreeningToScreeningResponse(screening);
    }

    @Override
    public void createScreening(ScreeningRequest screening) {
        validateCinemaManagerIsManagingACinema(screening.email());
        final var cinema = getCinemaByUserEmail(screening.email());
        final var newScreening = screeningMapper.mapScreeningRequestToScreening(screening, cinema);
        cinema.getRepertory().add(newScreening);
        cinemaRepository.save(cinema);
    }

    @Override
    public void updateScreening(Long id, ScreeningRequest screening) {
        validateCinemaManagerIsManagingACinema(screening.email());
        final var cinema = getCinemaByUserEmail(screening.email());
        final var screeningToUpdate = getScreeningById(id);
        cinema.removeScreening(screeningToUpdate);
        updateScreeningDetails(screeningToUpdate, screening);
        cinema.addScreening(screeningToUpdate);
        cinemaRepository.save(cinema);
    }

    @Override
    public void deleteScreening(Long id) {
        final var screening = getScreeningById(id);
        final var cinema = getCinemaWhichContainsScreening(screening);
        cinema.getRepertory().remove(screening);
        cinemaRepository.save(cinema);
    }

    private Cinema getCinemaWhichContainsScreening(Screening screening) {
        return cinemaRepository.findByRepertoryContains(screening).orElseThrow();
    }

    private Screening getScreeningById(Long id) {
        return screeningRepository.findById(id).orElseThrow();
    }

    private Cinema getCinemaByUserEmail(String email) {
        return cinemaRepository.findByUserEmail(email).orElseThrow();
    }

    private void validateCinemaManagerIsManagingACinema(String email) {
        if (!cinemaRepository.existsByCinemaManagerEmail(email)) {
            throw new IllegalArgumentException("You are not assigned to any cinema");
        }
    }

    private void updateScreeningDetails(Screening screeningToUpdate, ScreeningRequest screeningRequest) {
        screeningToUpdate.setMovie(movieRepository.findByTitle(screeningRequest.movieTitle()).orElseThrow());
        screeningToUpdate.setStartTime(screeningRequest.startTime());
        screeningToUpdate.setEndTime(screeningRequest.endTime());
        screeningToUpdate.setScreeningRoom(screeningRoomRepository.findByName(screeningRequest.screeningRoom()).orElseThrow());
    }
}
