package org.example.cinemabackend.cinema.application.dto.request.create;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.example.cinemabackend.cinema.core.domain.SeatZone;
import org.example.cinemabackend.cinema.core.domain.Status;

@Builder
public record CreatSeatRequest(
        @NotNull Integer seatRow,
        @NotNull Integer seatColumn,
        @NotNull SeatZone seatZone,
        @NotNull Status status
) {
}
