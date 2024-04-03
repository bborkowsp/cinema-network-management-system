package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;

@Builder
public record ProjectionTechnologyResponse(
        String technology,
        String description
) {
}
