package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.CreatSeatRequest;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.domain.SeatRow;

import java.util.List;

public interface SeatMapper {

    Seat mapCreateSeatToSeat(CreatSeatRequest seat);

    List<SeatRow> mapCreateSeatToSeatGrid(CreatSeatRequest[][] seats);
}
