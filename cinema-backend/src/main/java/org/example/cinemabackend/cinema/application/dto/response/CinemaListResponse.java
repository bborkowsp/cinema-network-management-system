package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;

@Builder
public record CinemaListResponse(
        String name,
        String cinemaManager,
        int numberOfScreeningRooms,
        int numberOfAvailableSeats,
        int numberOfUnavailableSeats
) {
}
