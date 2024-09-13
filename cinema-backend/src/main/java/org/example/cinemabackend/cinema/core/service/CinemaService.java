package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend._shared.service.FileService;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaListResponse;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.port.primary.CinemaMapper;
import org.example.cinemabackend.cinema.core.port.primary.CinemaUseCases;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class CinemaService implements CinemaUseCases {
    private static final String CINEMA_IMAGES_DIRECTORY = "images\\cinemas-images\\";
    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;
    private final FileService fileService;

    @Override
    public List<CinemaListResponse> getCinemas() {
        return cinemaRepository.findAll().stream().map(cinemaMapper::mapCinemaToCinemaListResponse).toList();
    }

    @Override
    public List<String> getCinemaNames() {
        return cinemaRepository.findAllCinemaNames();
    }

    @Override
    public CinemaResponse getCinema(String name) {
        final var cinema = cinemaRepository.findByName(name).orElseThrow();
        return cinemaMapper.mapCinemaToCinemaResponse(cinema);
    }

    @Override
    public void createCinema(MultipartFile image, CreateCinemaRequest createCinemaRequest) {
        validateCinemaDoesNotExist(createCinemaRequest.name());
        validateCinemaManagerIsNotAssignedToAnyCinema(createCinemaRequest.cinemaManager().email());
        final var cinema = cinemaMapper.mapCreateCinemaRequestToCinema(createCinemaRequest);
        cinema.setImage(fileService.saveImageToFileSystem(image, CINEMA_IMAGES_DIRECTORY));
        cinemaRepository.save(cinema);
    }

    @Override
    public void updateCinema(String name, MultipartFile image, UpdateCinemaRequest updateCinemaRequest) {
        validateCinemaNameIsNotTakenWhenUpdate(name, updateCinemaRequest.name());
        final var cinema = cinemaRepository.findByName(name).orElseThrow();
        validateCinemaManagerIsNotAssignedWhenUpdate(cinema, updateCinemaRequest.cinemaManager().email());
        cinemaMapper.updateCinemaFromUpdateCinemaRequest(updateCinemaRequest, cinema);
        handleImageUpdate(image, cinema);
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

    private void handleImageUpdate(MultipartFile image, Cinema cinema) {
        if (image != null && !image.isEmpty()) {
            fileService.deleteOldImageFromFileSystem(cinema.getImage(), CINEMA_IMAGES_DIRECTORY);
            cinema.setImage(fileService.saveImageToFileSystem(image, CINEMA_IMAGES_DIRECTORY));
        }
    }


    private void validateCinemaDoesNotExist(String name) {
        if (cinemaRepository.existsByName(name)) {
            throw new IllegalStateException("Cinema with name " + name + " already exists.");
        }
    }

    private void validateCinemaManagerIsNotAssignedToAnyCinema(String email) {
        if (cinemaRepository.existsByCinemaManagerEmail(email)) {
            throw new IllegalStateException("Cinema manager with email " + email + " is already assigned to a cinema.");
        }
    }
}
