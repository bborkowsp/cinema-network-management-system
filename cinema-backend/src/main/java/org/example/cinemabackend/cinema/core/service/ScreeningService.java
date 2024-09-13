package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.auth.core.port.primary.AuthUseCases;
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
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
class ScreeningService implements ScreeningUseCases {
    private final MovieMapper movieMapper;
    private final ScreeningMapper screeningMapper;
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final ScreeningRepository screeningRepository;
    private final ScreeningRoomRepository screeningRoomRepository;
    private final AuthUseCases authUseCases;

    @Override
    public List<ScreeningResponse> getScreenings(String email) {
        validateCinemaExistsByCinemaManager(email);
        authUseCases.validateIfEmailFromRequestMatchesEmailInJWT(email);
        return getScreeningsForCinemaManager(email);
    }

    @Override
    public List<ScreeningResponse> getRepertory(String cinema) {
        return cinemaRepository.findByName(cinema)
                .map(this::mapScreeningsFromCinema)
                .orElse(Collections.emptyList());
    }

    @Override
    public List<ScreeningResponse> getRepertoryAtSpecificDate(String cinema, LocalDate date) {
        return cinemaRepository.findByName(cinema)
                .map(cinemaSchema -> mapScreeningsFromCinemaAtSpecificDate(cinemaSchema, date))
                .orElse(Collections.emptyList());
    }

    @Override
    public ScreeningResponse getScreening(Long id) {
        final var screening = getScreeningById(id);
        final var screeningRoom = getScreeningRoomWhichContainsScreening(screening);
        return screeningMapper.mapScreeningToScreeningResponse(screening, screeningRoom);
    }

    @Override
    public ScreeningDetailsResponse getScreeningDetails(String title, LocalDate date) {
        final var movie = movieRepository.findByTitle(title).orElseThrow();
        final var screenings = cinemaRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Cinema::getName,
                        cinema -> mapScreeningsFromCinemaForTitleAndDate(cinema, title, date)
                ));
        return new ScreeningDetailsResponse(movieMapper.mapMovieToMovieResponse(movie), screenings);
    }


    @Override
    public void createScreening(CreateScreeningRequest createScreeningRequest) {
        validateCinemaExistsByCinemaManager(createScreeningRequest.email());
        final var newScreening = screeningMapper.mapScreeningRequestToScreening(createScreeningRequest);
        final var screeningRoom = getScreeningRoom(createScreeningRequest);
        screeningRoom.addScreening(newScreening);
        screeningRoomRepository.save(screeningRoom);
    }

    @Override
    public void updateScreening(Long id, CreateScreeningRequest createScreeningRequest) {
        validateCinemaExistsByCinemaManager(createScreeningRequest.email());
        final var screeningRoom = getScreeningRoom(createScreeningRequest);
        final var screeningToUpdate = getScreeningById(id);
        screeningRoom.getRepertory().remove(screeningToUpdate);
        updateScreeningDetails(screeningToUpdate, createScreeningRequest);
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

    private ScreeningRoom getScreeningRoom(CreateScreeningRequest createScreeningRequest) {
        final var cinema = cinemaRepository.findByUserEmail(createScreeningRequest.email()).orElseThrow();
        return cinema.getScreeningRooms().stream()
                .filter(screeningRoom -> screeningRoom.getName().equals(createScreeningRequest.screeningRoom()))
                .findFirst()
                .orElseThrow();
    }

    private List<ScreeningResponse> mapScreeningsFromCinemaForTitleAndDate(Cinema cinema, String title, LocalDate date) {
        return cinema.getScreeningRooms().stream()
                .flatMap(screeningRoom -> mapScreeningsFromScreeningRoomForTitleAndDate(screeningRoom, title, date))
                .collect(Collectors.toList());
    }

    private Stream<ScreeningResponse> mapScreeningsFromScreeningRoomForTitleAndDate(ScreeningRoom screeningRoom, String title, LocalDate date) {
        return screeningRoom.getRepertory().stream()
                .filter(screening -> screening.getMovie().getTitle().equals(title) &&
                        screening.getStartTime().toLocalDate().equals(date))
                .map(screening -> screeningMapper.mapScreeningToScreeningResponse(screening, screeningRoom));
    }

    private Screening getScreeningById(Long id) {
        return screeningRepository.findById(id).orElseThrow();
    }

    private ScreeningRoom getScreeningRoomWhichContainsScreening(Screening screening) {
        return screeningRoomRepository.findByRepertoryContains(screening).orElseThrow();
    }

    private List<ScreeningResponse> mapScreeningsFromCinemaAtSpecificDate(Cinema cinema, LocalDate date) {
        return cinema.getScreeningRooms().stream()
                .flatMap(screeningRoom -> mapScreeningsFromScreeningRoomAtSpecificDate(screeningRoom, date))
                .collect(Collectors.toList());
    }

    private Stream<ScreeningResponse> mapScreeningsFromScreeningRoomAtSpecificDate(ScreeningRoom screeningRoom, LocalDate date) {
        return screeningRoom.getRepertory().stream()
                .filter(screening -> screening.getStartTime().toLocalDate().equals(date))
                .map(screening -> screeningMapper.mapScreeningToScreeningResponse(screening, screeningRoom));
    }

    private void validateCinemaExistsByCinemaManager(String email) {
        if (!cinemaRepository.existsByCinemaManagerEmail(email)) {
            throw new IllegalStateException("You are not assigned to any cinema");
        }
    }

    private List<ScreeningResponse> getScreeningsForCinemaManager(String email) {
        return cinemaRepository.findByUserEmail(email)
                .map(this::mapScreeningsFromCinema)
                .orElse(Collections.emptyList());
    }

    private List<ScreeningResponse> mapScreeningsFromCinema(Cinema cinema) {
        return cinema.getScreeningRooms().stream()
                .flatMap(this::mapScreeningsFromScreeningRoom)
                .collect(Collectors.toList());
    }

    private Stream<ScreeningResponse> mapScreeningsFromScreeningRoom(ScreeningRoom screeningRoom) {
        return screeningRoom.getRepertory().stream()
                .map(screening -> screeningMapper.mapScreeningToScreeningResponse(screening, screeningRoom));
    }
}
