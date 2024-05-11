package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.CreatSeatRequest;
import org.example.cinemabackend.cinema.core.domain.Seat;

import java.util.Set;

public interface SeatMapper {
    Set<Seat> mapCreateSeatToSeat(Set<CreatSeatRequest> seats);

    Seat mapCreateSeatToSeat(CreatSeatRequest seat);
}
