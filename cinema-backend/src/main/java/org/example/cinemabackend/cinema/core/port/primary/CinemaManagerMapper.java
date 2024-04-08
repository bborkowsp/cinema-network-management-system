package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.user.core.domain.CinemaManager;

public interface CinemaManagerMapper {
    CinemaManagerResponse mapCinemaManagerToCinemaManagerResponse(CinemaManager cinemaManager);
}
