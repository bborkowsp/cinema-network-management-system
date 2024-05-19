package org.example.cinemabackend.cinema.application.dto.request;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ScreeningRequest(
        String title,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String screeningRoomName
) {
}
