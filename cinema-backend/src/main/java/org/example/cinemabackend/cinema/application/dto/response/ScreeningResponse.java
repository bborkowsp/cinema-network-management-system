package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;

import java.time.LocalDateTime;

@Builder
public record ScreeningResponse(
        MovieResponse movie,
        LocalDateTime startTime,
        LocalDateTime endTime,
        ScreeningRoomResponse screeningRoom
) {
}
