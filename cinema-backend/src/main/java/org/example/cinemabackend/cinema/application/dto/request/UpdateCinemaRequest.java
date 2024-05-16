package org.example.cinemabackend.cinema.application.dto.request;

import lombok.Builder;

@Builder
public record UpdateCinemaRequest(
        String name
) {
}
