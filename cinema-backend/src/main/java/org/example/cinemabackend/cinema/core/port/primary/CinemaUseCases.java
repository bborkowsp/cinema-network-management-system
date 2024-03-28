package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;

import java.util.List;

public interface CinemaUseCases {
    CinemaResponse getCinema(String name);

    List<CinemaResponse> getCinemas();

    void createCinema(CreateCinemaRequest createCinemaRequest);
}
