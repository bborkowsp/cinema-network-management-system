package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.resource.CinemaResource;
import org.example.cinemabackend.cinema.core.domain.Cinema;

public interface CinemaMapper {
    CinemaResource mapCinemaToCinemaResource(Cinema cinema);
}
