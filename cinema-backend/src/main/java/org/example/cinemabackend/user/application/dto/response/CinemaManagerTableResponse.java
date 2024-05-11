package org.example.cinemabackend.user.application.dto.response;

import lombok.Builder;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;

@Builder
public record CinemaManagerTableResponse(
        String firstName,
        String lastName,
        String email,
        CinemaResponse managedCinema
) {
}
