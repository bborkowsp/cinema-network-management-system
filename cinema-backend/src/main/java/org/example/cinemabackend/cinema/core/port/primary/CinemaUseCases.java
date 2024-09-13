package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaListResponse;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CinemaUseCases {

    List<CinemaListResponse> getCinemas();

    List<String> getCinemaNames();

    CinemaResponse getCinema(String name);

    void createCinema(MultipartFile image, CreateCinemaRequest createCinemaRequest);

    void updateCinema(String name, MultipartFile image, UpdateCinemaRequest updateCinemaRequest);

    void deleteCinema(String name);
}
