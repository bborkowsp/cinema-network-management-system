package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.resource.CinemaResource;

import java.util.List;

public interface CinemaUseCases {
    CinemaResource getCinema(String name);

    List<CinemaResource> getCinemas();

}
