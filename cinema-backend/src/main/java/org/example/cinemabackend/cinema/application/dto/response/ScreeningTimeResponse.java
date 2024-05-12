package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ScreeningTimeResponse(
        ScreeningRoomResponse screeningRoom,
        LocalDateTime time
) {
}
