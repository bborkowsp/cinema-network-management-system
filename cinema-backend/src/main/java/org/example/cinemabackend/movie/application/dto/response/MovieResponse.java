package org.example.cinemabackend.movie.application.dto.response;

import lombok.Builder;
import org.example.cinemabackend.cinema.application.dto.response.ImageResponse;

@Builder
public record MovieResponse(
        String title,
        ImageResponse image
) {
}
