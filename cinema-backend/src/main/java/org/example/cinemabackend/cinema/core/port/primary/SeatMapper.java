package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.create.CreatSeatRequest;
import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.domain.SeatRow;

import java.util.List;

public interface SeatMapper {

    SeatResponse[][] mapSeatRowsToSeatResponses(List<SeatRow> seatRows);

    Seat mapCreateSeatRequestToSeat(CreatSeatRequest seatRequest);

    List<Seat> mapSeatResponsesToSeat(List<SeatResponse> seatResponses);

    Seat mapSeatResponseToSeat(SeatResponse seatResponse);

    Seat mapCreateSeatToSeat(CreatSeatRequest seat);

    SeatResponse mapSeatToSeatResponse(Seat seat);
}
