package org.example.cinemabackend.ticketing.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;

import java.math.BigDecimal;
import java.util.List;

public interface SeatReservationUseCases {
    BigDecimal getOrderFee(List<SeatResponse> buyTicketRequest);

    void validateSeatsAreAvailable(List<SeatResponse> seatResponses);
}
