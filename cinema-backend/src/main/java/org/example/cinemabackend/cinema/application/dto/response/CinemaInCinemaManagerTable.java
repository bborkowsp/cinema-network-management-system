package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;

@Builder
public record CinemaInCinemaManagerTable(
        String name,
        AddressResponse address
) {
}
