package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;
import org.example.cinemabackend.movie.application.dto.response.MovieVariantResponse;

import java.time.LocalDateTime;

@Builder
public record ScreeningResponse(
        Long id,
        MovieResponse movie,
        LocalDateTime startTime,
        LocalDateTime endTime,
        ScreeningRoomResponse screeningRoom,
        MovieVariantResponse movieVariant
) {
}
