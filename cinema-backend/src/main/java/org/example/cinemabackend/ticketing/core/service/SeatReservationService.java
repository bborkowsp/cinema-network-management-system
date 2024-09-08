package org.example.cinemabackend.ticketing.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;
import org.example.cinemabackend.ticketing.core.port.primary.SeatReservationUseCases;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
@RequiredArgsConstructor
class SeatReservationService implements SeatReservationUseCases {
    @Override
    public BigDecimal getOrderFee(Set<SeatResponse> seatResponses) {
        BigDecimal fee = BigDecimal.ZERO;
        for (SeatResponse seatResponse : seatResponses) {
            BigDecimal price = seatResponse.seatZone().getPrice();
            fee = fee.add(price);
        }
        return fee;
    }

    @Override
    public void validateSeatsAreAvailable(Set<SeatResponse> seatResponses) {

    }
}
