package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningDetailsResponse;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningMapper;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningUseCases;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRoomRepository;
import org.example.cinemabackend.movie.core.port.primary.MovieMapper;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class ScreeningService implements ScreeningUseCases {
    private final ScreeningMapper screeningMapper;
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final ScreeningRepository screeningRepository;
    private final ScreeningRoomRepository screeningRoomRepository;
    private final MovieMapper movieMapper;

    @Override
    public ScreeningDetailsResponse getScreeningDetails(String title, LocalDate date) {
        final var movie = movieRepository.findByTitle(title).orElseThrow();
        final var screenings = cinemaRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Cinema::getName,
                        cinema -> cinema.getScreeningRooms().stream()
                                .flatMap(screeningRoom -> screeningRoom.getRepertory().stream()
                                        .filter(screening -> screening.getMovie().getTitle().equals(title) &&
                                                screening.getStartTime().toLocalDate().equals(date))
                                        .map(screening -> screeningMapper.mapScreeningToScreeningResponse(screening, screeningRoom)))
                                .collect(Collectors.toList())
                ));
        return new ScreeningDetailsResponse(movieMapper.mapMovieToMovieResponse(movie), screenings);
    }

    @Override
    public List<ScreeningResponse> getScreenings(String email) {
        validateCinemaManagerIsManagingACinema(email);
        return cinemaRepository.findByUserEmail(email)
                .map(cinema -> cinema.getScreeningRooms().stream()
                        .flatMap(screeningRoom -> screeningRoom.getRepertory().stream()
                                .map(screening -> screeningMapper.mapScreeningToScreeningResponse(screening, screeningRoom)))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    public List<ScreeningResponse> getRepertory(String cinema) {
        return cinemaRepository.findByName(cinema)
                .map(cinemaSchema -> cinemaSchema.getScreeningRooms().stream()
                        .flatMap(screeningRoom -> screeningRoom.getRepertory().stream()
                                .map(screening -> screeningMapper.mapScreeningToScreeningResponse(screening, screeningRoom)))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    public List<ScreeningResponse> getRepertoryAtSpecificDate(String cinema, LocalDate date) {
        return cinemaRepository.findByName(cinema)
                .map(cinemaSchema -> cinemaSchema.getScreeningRooms().stream()
                        .flatMap(screeningRoom -> screeningRoom.getRepertory().stream()
                                .filter(screening -> screening.getStartTime().toLocalDate().equals(date))
                                .map(screening -> screeningMapper.mapScreeningToScreeningResponse(screening, screeningRoom)))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    public ScreeningResponse getScreening(Long id) {
        final var screening = getScreeningById(id);
        final var screeningRoom = getScreeningRoomWhichContainsScreening(screening);
        return screeningMapper.mapScreeningToScreeningResponse(screening, screeningRoom);
    }

    @Override
    public void createScreening(CreateScreeningRequest screening) {
        validateCinemaManagerIsManagingACinema(screening.email());
        final var newScreening = screeningMapper.mapScreeningRequestToScreening(screening);
        final var screeningRoom = screeningRoomRepository.findByName(screening.screeningRoom()).orElseThrow();
        screeningRoom.addScreening(newScreening);
        screeningRoomRepository.save(screeningRoom);
    }

    @Override
    public void updateScreening(Long id, CreateScreeningRequest screening) {
        validateCinemaManagerIsManagingACinema(screening.email());
        final var screeningRoom = screeningRoomRepository.findByName(screening.screeningRoom()).orElseThrow();
        final var screeningToUpdate = getScreeningById(id);
        screeningRoom.getRepertory().remove(screeningToUpdate);
        updateScreeningDetails(screeningToUpdate, screening);
        screeningRoom.getRepertory().add(screeningToUpdate);
        screeningRoomRepository.save(screeningRoom);
    }

    @Override
    public void deleteScreening(Long id) {
        final var screening = getScreeningById(id);
        final var screeningRoom = getScreeningRoomWhichContainsScreening(screening);
        screeningRoom.getRepertory().remove(screening);
        screeningRoomRepository.save(screeningRoom);
    }

    private void updateScreeningDetails(Screening screeningToUpdate, CreateScreeningRequest createScreeningRequest) {
        screeningToUpdate.setMovie(movieRepository.findByTitle(createScreeningRequest.movieTitle()).orElseThrow());
        screeningToUpdate.setStartTime(createScreeningRequest.startTime());
        screeningToUpdate.setEndTime(createScreeningRequest.endTime());
    }

    private Screening getScreeningById(Long id) {
        return screeningRepository.findById(id).orElseThrow();
    }

    private ScreeningRoom getScreeningRoomWhichContainsScreening(Screening screening) {
        return screeningRoomRepository.findByRepertoryContains(screening).orElseThrow();
    }

    private void validateCinemaManagerIsManagingACinema(String email) {
        if (!cinemaRepository.existsByCinemaManagerEmail(email)) {
            throw new IllegalStateException("You are not assigned to any cinema");
        }
    }
}
