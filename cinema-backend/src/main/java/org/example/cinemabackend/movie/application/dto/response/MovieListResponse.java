package org.example.cinemabackend.movie.application.dto.response;

import lombok.Builder;
import org.example.cinemabackend.cinema.application.dto.response.ImageResponse;

import java.time.LocalDate;

@Builder
public record MovieListResponse(
        String title,
        String originalTitle,
        Double duration,
        LocalDate releaseDate,
        ImageResponse image,
        FilmMemberResponse director
) {
}
