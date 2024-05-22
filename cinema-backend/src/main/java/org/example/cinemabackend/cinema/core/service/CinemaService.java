package org.example.cinemabackend.cinema.core.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;
import org.example.cinemabackend.cinema.application.dto.response.CinemaTableResponse;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.primary.CinemaMapper;
import org.example.cinemabackend.cinema.core.port.primary.CinemaUseCases;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class CinemaService implements CinemaUseCases {

    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;

    @Override
    public List<CinemaTableResponse> getCinemas() {
        return cinemaRepository.findAll().stream().map(cinemaMapper::mapCinemaToCinemaTableRow).toList();
    }

    @Override
    public List<String> getCinemaNames() {
        return cinemaRepository.findAll().stream().map(Cinema::getName).toList();
    }

    @Override
    public List<String> getScreeningRoomsNames(String email) {
        final var cinema = cinemaRepository.findByUserEmail(email);
        return cinema.getScreeningRooms().stream().map(ScreeningRoom::getName).toList();
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
    public void updateCinema(String name, UpdateCinemaRequest updateCinemaRequest) {
        validateCinemaNameIsNotTaken(name, updateCinemaRequest.name());

        final var cinema = cinemaRepository.findByName(name).orElseThrow();
        cinemaMapper.updateCinemaFromUpdateCinemaRequest(updateCinemaRequest, cinema);
        cinemaRepository.save(cinema);
    }

    @Override
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

    private void validateCinemaNameIsNotTaken(String name, String newName) {
        if (!name.equals(newName) && cinemaRepository.existsByName(newName)) {
            throw new IllegalArgumentException("Cinema with name " + newName + " already exists.");
        }
    }
}
