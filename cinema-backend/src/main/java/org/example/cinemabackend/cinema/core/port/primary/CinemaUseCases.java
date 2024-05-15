package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;
import org.example.cinemabackend.cinema.application.dto.response.CinemaTableResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CinemaUseCases {
    CinemaResponse getCinema(String name);

    Page<CinemaTableResponse> getCinemas(Pageable pageable);

    List<CinemaTableResponse> getCinemas();


    void createCinema(CreateCinemaRequest createCinemaRequest);

    void deleteCinema(String name);

    List<String> getCinemaNames();
}
