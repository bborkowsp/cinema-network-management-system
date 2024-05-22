package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.ScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningMapper;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningUseCases;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRoomRepository;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ScreeningService implements ScreeningUseCases {

    private final ScreeningRepository screeningRepository;
    private final CinemaRepository cinemaRepository;
    private final ScreeningMapper screeningMapper;
    private final MovieRepository movieRepository;
    private final ScreeningRoomRepository screeningRoomRepository;

    @Override
    public List<ScreeningResponse> getScreenings() {
        return screeningRepository.findAll().stream().map(screeningMapper::mapScreeningToScreeningResponse).toList();
    }

    @Override
    public List<ScreeningResponse> getScreenings(String email) {
        final var cinema = cinemaRepository.findByUserEmail(email);
        return cinema.getRepertory().stream().map(screeningMapper::mapScreeningToScreeningResponse).toList();
    }

    @Override
    public void updateScreening(Long id, UpdateScreeningRequest screening) {
        final var screeningToUpdate = screeningRepository.findById(id).orElseThrow();
        screeningToUpdate.setMovie(movieRepository.findByTitle(screening.movieTitle()).orElseThrow());
        screeningToUpdate.setStartTime(screening.startTime());
        screeningToUpdate.setEndTime(screening.endTime());
        screeningToUpdate.setScreeningRoom(screeningRoomRepository.findByName(screening.screeningRoom()).orElseThrow());
        screeningRepository.save(screeningToUpdate);
    }

    @Override
    public void deleteScreening(Long id) {
        screeningRepository.deleteById(id);
    }

    @Override
    public ScreeningResponse getScreening(Long id) {
        final var screening = screeningRepository.findById(id).orElseThrow();
        return screeningMapper.mapScreeningToScreeningResponse(screening);
    }

    @Override
    public void createScreening(ScreeningRequest screening) {
        final var newScreening = screeningMapper.mapScreeningRequestToScreening(screening);
        screeningRepository.save(newScreening);
    }
}
