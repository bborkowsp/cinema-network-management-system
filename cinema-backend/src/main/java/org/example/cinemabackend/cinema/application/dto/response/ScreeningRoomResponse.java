package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record ScreeningRoomResponse(
        String name,
        List<SeatRowResponse> seatRows,
        Set<ProjectionTechnologyResponse> supportedTechnologies
) {
}
