package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;
import org.example.cinemabackend.cinema.core.domain.SeatType;
import org.example.cinemabackend.cinema.core.domain.SeatZone;

@Builder
public record SeatResponse(
        Integer seatRow,
        Integer seatColumn,
        SeatZone seatZone,
        SeatType seatType
) {
}
