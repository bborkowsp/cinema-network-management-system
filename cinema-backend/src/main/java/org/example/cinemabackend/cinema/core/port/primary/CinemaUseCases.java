package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;
import org.example.cinemabackend.cinema.application.dto.response.CinemaTableResponse;

import java.util.List;

public interface CinemaUseCases {

    List<CinemaTableResponse> getCinemas();

    List<String> getCinemaNames();

    List<String> getScreeningRoomsNames(String email);

    CinemaResponse getCinema(String name);


    void createCinema(CreateCinemaRequest createCinemaRequest);

    void updateCinema(String name, UpdateCinemaRequest updateCinemaRequest);

    void deleteCinema(String name);
}
