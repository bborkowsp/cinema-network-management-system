package org.example.cinemabackend.movie.application.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MovieListResponse(
        String title,
        String originalTitle,
        Integer duration,
        LocalDate releaseDate,
        String poster,
        FilmMemberResponse director
) {
}
