package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.ScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;

public interface ScreeningMapper {
    ScreeningResponse mapScreeningToScreeningResponse(Screening screening, ScreeningRoom screeningRoom);

    Screening mapScreeningRequestToScreening(ScreeningRequest screening);
}
