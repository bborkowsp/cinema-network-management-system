package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;

@Builder
public record ImageResponse(
        String name,
        String type,
        byte[] data
) {
}
