package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.resource.CinemaResource;

public interface CinemaUseCases {
    CinemaResource getCinema(String name);
}
