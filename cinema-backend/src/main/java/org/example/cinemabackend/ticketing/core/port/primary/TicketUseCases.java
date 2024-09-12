package org.example.cinemabackend.ticketing.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.domain.SeatStatus;
import org.example.cinemabackend.ticketing.application.dto.request.BuyTicketRequest;

import java.math.BigDecimal;
import java.util.List;

public interface TicketUseCases {
    void generateTickets(BuyTicketRequest buyTicketRequest, String orderId);

    void changeSeatStatus(List<Seat> seatResponses, SeatStatus seatStatus);

    void validateSeatsAreAvailable(List<SeatResponse> seatResponses);

    BigDecimal getOrderFee(List<SeatResponse> buyTicketRequest);
}
