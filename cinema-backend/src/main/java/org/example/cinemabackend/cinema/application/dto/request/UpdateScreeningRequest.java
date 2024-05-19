package org.example.cinemabackend.cinema.application.dto.request;

import lombok.Builder;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningRoomResponse;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;

import java.time.LocalDateTime;

@Builder
public record UpdateScreeningRequest(
        MovieResponse movie,
        LocalDateTime startTime,
        LocalDateTime endTime,
        ScreeningRoomResponse screeningRoom
) {
}
