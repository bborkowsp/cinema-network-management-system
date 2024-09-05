package org.example.cinemabackend.cinema.core.service;

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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class CinemaService implements CinemaUseCases {
    private static final String UPLOAD_DIRECTORY = "cinemas-images\\";
    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;

    @Override
    public List<CinemaTableResponse> getCinemas() {
        return cinemaRepository.findAll().stream().map(cinemaMapper::mapCinemaToCinemaTableRow).toList();
    }

    @Override
    public List<String> getCinemaNames() {
        return cinemaRepository.findAllCinemaNames();
    }

    @Override
    public List<String> getScreeningRoomsNames(String email) {
        final var cinema = getCinemaByUserEmail(email);
        return cinema.getScreeningRooms().stream().map(ScreeningRoom::getName).toList();
    }

    @Override
    public CinemaResponse getCinema(String name) {
        final var cinema = cinemaRepository.findByName(name).orElseThrow();
        return cinemaMapper.mapCinemaToCinemaResponse(cinema);
    }

    @Override
    public void createCinema(MultipartFile image, CreateCinemaRequest createCinemaRequest) {
        validateCinemaDoesntExist(createCinemaRequest.name());
        validateCinemaManagerIsNotAssigned(createCinemaRequest.cinemaManager().email());
        final var cinema = cinemaMapper.mapCreateCinemaRequestToCinema(createCinemaRequest);
        cinema.setImage(saveImageToFileSystem(image));
        cinemaRepository.save(cinema);
    }

    @Override
    public void updateCinema(String name, MultipartFile image, UpdateCinemaRequest updateCinemaRequest) {
        validateCinemaNameIsNotTakenWhenUpdate(name, updateCinemaRequest.name());
        final var cinema = cinemaRepository.findByName(name).orElseThrow();
        validateCinemaManagerIsNotAssignedWhenUpdate(cinema, updateCinemaRequest.cinemaManager().email());
        cinemaMapper.updateCinemaFromUpdateCinemaRequest(updateCinemaRequest, cinema);
        if (image != null && !image.isEmpty()) {
            deleteOldImageFromFileSystem(cinema.getImage());
            cinema.setImage(saveImageToFileSystem(image));
        }
        cinemaRepository.save(cinema);
    }

    @Override
    public void deleteCinema(String name) {
        validateCinemaExists(name);
        cinemaRepository.deleteByName(name);
    }

    private void validateCinemaExists(String name) {
        if (!cinemaRepository.existsByName(name)) {
            throw new IllegalStateException("Cinema with name " + name + " doesn't exist.");
        }
    }

    private void validateCinemaNameIsNotTakenWhenUpdate(String name, String newName) {
        if (!name.equals(newName) && cinemaRepository.existsByName(newName)) {
            throw new IllegalStateException("Cinema with name " + newName + " already exists.");
        }
    }

    private void validateCinemaManagerIsNotAssignedWhenUpdate(Cinema cinema, String email) {
        if (cinemaRepository.existsByCinemaManagerEmail(email) && email != null && !Objects.equals(cinema.getCinemaManager().getEmail(), email)) {
            throw new IllegalStateException("Cinema manager with email " + email + " is already assigned to a cinema.");
        }
    }

    private void deleteOldImageFromFileSystem(String image) {
        try {
            Files.deleteIfExists(Paths.get(UPLOAD_DIRECTORY + image));
        } catch (IOException e) {
        }
    }

    private void validateCinemaDoesntExist(String name) {
        if (cinemaRepository.existsByName(name)) {
            throw new IllegalStateException("Cinema with name " + name + " already exists.");
        }
    }

    private void validateCinemaManagerIsNotAssigned(String email) {
        if (cinemaRepository.existsByCinemaManagerEmail(email)) {
            throw new IllegalStateException("Cinema manager with email " + email + " is already assigned to a cinema.");
        }
    }

    private String saveImageToFileSystem(MultipartFile image) {
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIRECTORY + fileName);
        try {
            Files.write(filePath, image.getBytes());
        } catch (IOException e) {
        }
        return fileName;
    }

    private Cinema getCinemaByUserEmail(String email) {
        return cinemaRepository.findByUserEmail(email).orElseThrow();
    }
}
