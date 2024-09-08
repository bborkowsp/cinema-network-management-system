package org.example.cinemabackend.ticketing.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;

import java.math.BigDecimal;
import java.util.Set;

public interface SeatReservationUseCases {
    BigDecimal getOrderFee(Set<SeatResponse> buyTicketRequest);

    void validateSeatsAreAvailable(Set<SeatResponse> seatResponses);
}
