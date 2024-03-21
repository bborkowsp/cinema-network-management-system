package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.resource.CinemaResource;
import org.example.cinemabackend.cinema.core.domain.Cinema;

public interface CinemaMapper {
    CinemaResource mapCinemaToCinemaResource(Cinema cinema);

    Cinema mapCreateCinemaRequestToCinema(CreateCinemaRequest createCinemaRequest);
}
