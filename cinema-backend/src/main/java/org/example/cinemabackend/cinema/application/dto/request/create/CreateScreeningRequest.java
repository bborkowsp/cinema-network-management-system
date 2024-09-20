package org.example.cinemabackend.cinema.application.dto.request.create;

import lombok.Builder;
import org.example.cinemabackend.movie.application.dto.response.MovieVariantResponse;

import java.time.LocalDateTime;

@Builder
public record CreateScreeningRequest(
        String movieTitle,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String screeningRoom,
        MovieVariantResponse movieVariant
) {
}
