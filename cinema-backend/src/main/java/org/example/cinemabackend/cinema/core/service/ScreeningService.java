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
import org.example.cinemabackend.movie.core.port.primary.MovieVariantMapper;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
class ScreeningService implements ScreeningUseCases {
    private final MovieMapper movieMapper;
    private final ScreeningMapper screeningMapper;
    private final MovieVariantMapper movieVariantMapper;
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final ScreeningRepository screeningRepository;
    private final ScreeningRoomRepository screeningRoomRepository;
    private final AuthUseCases authUseCases;

    @Override
    public List<ScreeningResponse> getScreenings() {
        final var email = authUseCases.getCurrentUserEmail();
        validateCinemaExistsByCinemaManager(email);
        return getScreeningsForCinemaManager(email);
    }

    @Override
    public List<ScreeningResponse> getRepertoryByCinemaAndDate(String cinema, LocalDate date) {
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
        final var email = authUseCases.getCurrentUserEmail();
        validateCinemaExistsByCinemaManager(email);
        final var newScreening = screeningMapper.mapScreeningRequestToScreening(createScreeningRequest);
        final var screeningRoom = getNewScreeningRoom(createScreeningRequest, email);
        validateNoScreeningIsPlayedAtTheSameTime(screeningRoom, newScreening);
        screeningRoom.addScreening(newScreening);
        screeningRoomRepository.save(screeningRoom);
    }

    @Override
    public void updateScreening(Long id, CreateScreeningRequest createScreeningRequest) {
        final var email = authUseCases.getCurrentUserEmail();
        validateCinemaExistsByCinemaManager(email);
        final var screeningToUpdate = getScreeningById(id);
        final var oldScreeningRoom = screeningRoomRepository.findByScreeningId(id).orElseThrow();
        final var newScreeningRoom = getNewScreeningRoom(createScreeningRequest, email);
        if (!oldScreeningRoom.equals(newScreeningRoom)) {
            oldScreeningRoom.getRepertory().remove(screeningToUpdate);
            updateScreeningDetails(screeningToUpdate, createScreeningRequest);
            validateNoScreeningIsPlayedAtTheSameTime(newScreeningRoom, screeningToUpdate);
            newScreeningRoom.addScreening(screeningToUpdate);
            screeningRoomRepository.save(oldScreeningRoom);
            screeningRoomRepository.save(newScreeningRoom);
        } else {
            updateScreeningDetails(screeningToUpdate, createScreeningRequest);
            validateNoScreeningIsPlayedAtTheSameTime(oldScreeningRoom, screeningToUpdate);
            screeningRepository.save(screeningToUpdate);
        }
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
        screeningToUpdate.setMovieVariant(movieVariantMapper.mapMovieVariantResponseToMovieVariant(createScreeningRequest.movieVariant()));
    }

    private void validateNoScreeningIsPlayedAtTheSameTime(ScreeningRoom screeningRoom, Screening screening) {
        screeningRoom.getRepertory().stream()
                .filter(existingScreening -> !existingScreening.getId().equals(screening.getId()))
                .filter(existingScreening -> timesOverlap(existingScreening, screening))
                .findAny()
                .ifPresent(existingScreening -> {
                    throw new IllegalStateException("Screening overlaps with another screening in the same room.");
                });
    }

    private boolean timesOverlap(Screening screening1, Screening screening2) {
        LocalDateTime start1 = screening1.getStartTime();
        LocalDateTime end1 = screening1.getEndTime();
        LocalDateTime start2 = screening2.getStartTime();
        LocalDateTime end2 = screening2.getEndTime();
        return start1.isBefore(end2) && end1.isAfter(start2);
    }

    private ScreeningRoom getNewScreeningRoom(CreateScreeningRequest createScreeningRequest, String email) {
        final var cinema = cinemaRepository.findByUserEmail(email).orElseThrow();
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
                        screening.getStartTime().toLocalDate().isEqual(date))
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
