package org.example.cinemabackend.ticketing.application.dto.response;

import lombok.Builder;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;
import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;

import java.util.List;

@Builder
public record TicketResponse(
        String qrCode,
        List<SeatResponse> bookedSeats,
        ScreeningResponse screening,
        String cinemaName
) {
}
