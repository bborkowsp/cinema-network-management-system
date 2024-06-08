package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.ScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;
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
        final var screening = screeningRepository.findById(id).orElseThrow();
        return screeningMapper.mapScreeningToScreeningResponse(screening);
    }

    @Override
    public void createScreening(ScreeningRequest screening) {
        final var cinema = cinemaRepository.findByUserEmail(screening.email()).orElseThrow();
        final var newScreening = screeningMapper.mapScreeningRequestToScreening(screening, cinema);
        cinema.getRepertory().add(newScreening);
        cinemaRepository.save(cinema);
    }

    @Override
    public void updateScreening(Long id, ScreeningRequest screening) {
        final var cinema = cinemaRepository.findByUserEmail(screening.email()).orElseThrow();
        final var screeningToUpdate = screeningRepository.findById(id).orElseThrow();
        cinema.getRepertory().remove(screeningToUpdate);
        screeningToUpdate.setMovie(movieRepository.findByTitle(screening.movieTitle()).orElseThrow());
        screeningToUpdate.setStartTime(screening.startTime());
        screeningToUpdate.setEndTime(screening.endTime());
        screeningToUpdate.setScreeningRoom(screeningRoomRepository.findByName(screening.screeningRoom()).orElseThrow());
        cinema.getRepertory().add(screeningToUpdate);
        cinemaRepository.save(cinema);
    }

    @Override
    public void deleteScreening(Long id) {
        final var screening = screeningRepository.findById(id).orElseThrow();
        final var cinema = cinemaRepository.findByRepertoryContains(screening).orElseThrow();
        cinema.getRepertory().remove(screening);
        cinemaRepository.save(cinema);
    }
}
