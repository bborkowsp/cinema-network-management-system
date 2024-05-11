package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaInCinemaManagerTable;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;
import org.example.cinemabackend.cinema.application.dto.response.CinemaTableResponse;
import org.example.cinemabackend.cinema.core.domain.Cinema;

public interface CinemaMapper {
    CinemaResponse mapCinemaToCinemaResponse(Cinema cinema);

    Cinema mapCreateCinemaRequestToCinema(CreateCinemaRequest createCinemaRequest);

    CinemaTableResponse mapCinemaToCinemaTableRow(Cinema cinema);

    CinemaInCinemaManagerTable mapCinemaToCinemaInCinemaManagerTable(Cinema cinema);
}
