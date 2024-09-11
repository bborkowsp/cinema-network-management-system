package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.create.CreatSeatRequest;
import org.example.cinemabackend.cinema.core.domain.SeatRow;

import java.util.List;

public interface SeatRowMapper {

    List<SeatRow> mapCreateSeatRowToSeatRow(CreatSeatRequest[][] seats);
}
