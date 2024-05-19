package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.ScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;
import org.example.cinemabackend.cinema.core.domain.Screening;

public interface ScreeningMapper {
    ScreeningResponse mapScreeningToScreeningResponse(Screening screening);

    Screening mapScreeningRequestToScreening(ScreeningRequest screening);
}
