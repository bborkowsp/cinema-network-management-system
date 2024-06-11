package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.ScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningMapper;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningUseCases;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRoomRepository;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        //  return screeningRepository.findAll().stream().map(screeningMapper::mapScreeningToScreeningResponse).toList();
        return null;
    }

    @Override
    public List<ScreeningResponse> getScreenings(String email) {
        final var cinema = cinemaRepository.findByUserEmail(email).orElse(null);
        if (cinema == null) {
            return Collections.emptyList();
        }
        List<ScreeningResponse> screeningResponses = new ArrayList<>();
        cinema.getScreeningRooms().forEach(screeningRoom -> {
            screeningResponses.addAll(screeningRoom.getRepertory().stream()
                    .map(screening -> screeningMapper.mapScreeningToScreeningResponse(screening, screeningRoom))
                    .toList());
        });
        if (screeningResponses.isEmpty()) {
            return Collections.emptyList();
        }
        return screeningResponses;
    }

    @Override
    public ScreeningResponse getScreening(Long id) {
        final var screening = getScreeningById(id);
        final var screeningRoom = getScreeningRoomWhichContainsScreening(screening);
        return screeningMapper.mapScreeningToScreeningResponse(screening, screeningRoom);
    }

    @Override
    public void createScreening(ScreeningRequest screening) {
        validateCinemaManagerIsManagingACinema(screening.email());
        final var newScreening = screeningMapper.mapScreeningRequestToScreening(screening);
        final var screeningRoom = screeningRoomRepository.findByName(screening.screeningRoom()).orElseThrow();
        screeningRoom.addScreening(newScreening);
        screeningRoomRepository.save(screeningRoom);
    }

    @Override
    public void updateScreening(Long id, ScreeningRequest screening) {
        validateCinemaManagerIsManagingACinema(screening.email());
        final var screeningRoom = screeningRoomRepository.findByName(screening.screeningRoom()).orElseThrow();
        final var screeningToUpdate = getScreeningById(id);
        final var oldScreeningRoom = getScreeningRoomWhichContainsScreening(screeningToUpdate);
        oldScreeningRoom.getRepertory().remove(screeningToUpdate);
        screeningRoomRepository.save(oldScreeningRoom);
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

    private ScreeningRoom getScreeningRoomWhichContainsScreening(Screening screening) {
        return screeningRoomRepository.findByRepertoryContains(screening).orElseThrow();
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
    }
}
