package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;
import org.example.cinemabackend.cinema.application.dto.response.CinemaTableResponse;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.springframework.lang.NonNull;

public interface CinemaMapper {

    CinemaTableResponse mapCinemaToCinemaTableRow(Cinema cinema);

    CinemaResponse mapCinemaToCinemaResponse(@NonNull Cinema cinema);

    Cinema mapCreateCinemaRequestToCinema(@NonNull CreateCinemaRequest createCinemaRequest);

    void updateCinemaFromUpdateCinemaRequest(@NonNull UpdateCinemaRequest updateCinemaRequest, Cinema cinema);
}
