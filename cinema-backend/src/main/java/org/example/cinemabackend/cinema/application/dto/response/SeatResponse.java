package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;
import org.example.cinemabackend.cinema.core.domain.SeatStatus;
import org.example.cinemabackend.cinema.core.domain.SeatZone;

import java.util.List;

@Builder
public record SeatResponse(
        Long id,
        Integer seatRow,
        Integer seatColumn,
        SeatZone seatZone,
        List<SeatStatus> status
) {
}
