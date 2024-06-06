package org.example.cinemabackend.cinema.application.dto.request;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ScreeningRequest(
        String movieTitle,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String screeningRoom,
        String email
) {
}
