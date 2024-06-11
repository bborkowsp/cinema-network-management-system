package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;

import java.util.Set;

@Builder
public record ScreeningRoomResponse(
        Long id,
        String name,
        SeatResponse[][] seats,
        Set<ProjectionTechnologyResponse> supportedTechnologies
) {
}
