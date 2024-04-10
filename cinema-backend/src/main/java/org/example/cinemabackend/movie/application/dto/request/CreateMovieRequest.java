package org.example.cinemabackend.movie.application.dto.request;

import lombok.Builder;

@Builder
public record CreateMovieRequest(
        String title
) {
}
