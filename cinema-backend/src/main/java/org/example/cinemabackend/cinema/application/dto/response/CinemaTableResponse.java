package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;

@Builder
public record CinemaTableResponse(
        String name,
        String cinemaManager,
        String numberOfScreeningRooms,
        String numberOfAvailableSeats,
        String numberOfUnavailableSeats
) {
}
