package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.response.SeatRowResponse;
import org.example.cinemabackend.cinema.core.domain.SeatRow;

public interface SeatRowMapper {
    SeatRowResponse mapSeatRowToSeatRowResponse(SeatRow seatRow);

}
