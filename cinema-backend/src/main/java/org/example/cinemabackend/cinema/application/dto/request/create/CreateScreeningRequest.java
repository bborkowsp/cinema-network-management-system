package org.example.cinemabackend.cinema.application.dto.request.create;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreateScreeningRequest(
        String movieTitle,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String screeningRoom,
        String email
) {
}
