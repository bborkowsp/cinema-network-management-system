package org.example.cinemabackend.cinema.application.dto.request.update;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.example.cinemabackend.cinema.core.domain.SeatType;
import org.example.cinemabackend.cinema.core.domain.SeatZone;

@Builder
public record UpdateSeatRequest(
        @NotNull Integer rowNumber,
        @NotNull Integer columnNumber,
        @NotNull SeatZone seatZone,
        @NotNull SeatType seatType
) {
}
