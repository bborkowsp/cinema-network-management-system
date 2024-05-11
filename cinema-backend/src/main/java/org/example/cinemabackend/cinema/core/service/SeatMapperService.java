package org.example.cinemabackend.cinema.core.service;

import org.example.cinemabackend.cinema.application.dto.request.CreatSeatRequest;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.port.primary.SeatMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
class SeatMapperService implements SeatMapper {

    @Override
    public Set<Seat> mapCreateSeatToSeat(Set<CreatSeatRequest> seats) {
        return seats.stream().map(this::mapCreateSeatToSeat).collect(Collectors.toSet());
    }

    @Override
    public Seat mapCreateSeatToSeat(CreatSeatRequest seat) {
        return new Seat(
                seat.rowNumber(),
                seat.columnNumber(),
                seat.seatZone(),
                seat.seatType()
        );
    }
}
