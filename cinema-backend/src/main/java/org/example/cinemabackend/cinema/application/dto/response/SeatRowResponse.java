package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record SeatRowResponse(
        List<SeatResponse> columnSeats
) {
}
