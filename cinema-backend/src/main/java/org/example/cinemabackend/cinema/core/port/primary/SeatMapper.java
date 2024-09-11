package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.create.CreatSeatRequest;
import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;
import org.example.cinemabackend.cinema.core.domain.Seat;

import java.util.Set;

public interface SeatMapper {

    Set<Seat> mapSeatResponsesToSeat(Set<SeatResponse> seatResponses);

    Seat mapSeatResponseToSeat(SeatResponse seatResponse);

    Seat mapCreateSeatToSeat(CreatSeatRequest seat);

    SeatResponse mapSeatToSeatResponse(Seat seat);

    Seat[][] mapCreateSeatRequestToSeat(CreatSeatRequest[][] seats);

    SeatResponse[][] mapSeatToSeatResponses(Seat[][] seatingPlan);
}
