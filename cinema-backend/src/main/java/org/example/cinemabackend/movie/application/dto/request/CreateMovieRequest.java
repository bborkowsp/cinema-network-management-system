package org.example.cinemabackend.movie.application.dto.request;

import lombok.Builder;
import org.example.cinemabackend.cinema.application.dto.request.CreateImageRequest;

@Builder
public record CreateMovieRequest(
        String title,
        CreateImageRequest image
) {
}
