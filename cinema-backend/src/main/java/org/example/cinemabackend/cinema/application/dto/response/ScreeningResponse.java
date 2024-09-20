package org.example.cinemabackend.cinema.application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;
import org.example.cinemabackend.movie.application.dto.response.MovieVariantResponse;

import java.time.LocalDateTime;

@Builder
public record ScreeningResponse(
        Long id,
        MovieResponse movie,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startTime,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endTime,
        ScreeningRoomResponse screeningRoom,
        MovieVariantResponse movieVariant
) {
}
