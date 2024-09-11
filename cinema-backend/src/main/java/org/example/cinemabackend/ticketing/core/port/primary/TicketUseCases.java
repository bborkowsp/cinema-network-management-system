package org.example.cinemabackend.ticketing.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.ticketing.application.dto.request.BuyTicketRequest;

import java.math.BigDecimal;
import java.util.Set;

public interface TicketUseCases {
    void changeSeatsStatusToReserved(Set<SeatResponse> seat, ScreeningRoom screeningRoom);

    BigDecimal getOrderFee(Set<SeatResponse> buyTicketRequest);

    ScreeningRoom validateSeatsAreAvailable(BuyTicketRequest seatResponses);
}
