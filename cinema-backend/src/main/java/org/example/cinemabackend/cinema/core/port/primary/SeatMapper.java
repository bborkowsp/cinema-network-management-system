package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.create.CreatSeatRequest;
import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.domain.SeatRow;

import java.util.List;
import java.util.Set;

public interface SeatMapper {

    SeatResponse[][] mapSeatRowsToSeatResponses(List<SeatRow> seatRows);

    Seat mapCreateSeatRequestToSeat(CreatSeatRequest seatRequest);

    Set<Seat> mapSeatResponsesToSeat(Set<SeatResponse> seatResponses);

    List<Seat> mapSeatResponsesToSeat(List<SeatResponse> seatResponses);

    Seat mapSeatResponseToSeat(SeatResponse seatResponse);

    Seat mapCreateSeatToSeat(CreatSeatRequest seat);

    SeatResponse mapSeatToSeatResponse(Seat seat);

    Seat[][] mapCreateSeatRequestToSeat(CreatSeatRequest[][] seats);

    SeatResponse[][] mapSeatToSeatResponses(Seat[][] seatingPlan);
}
