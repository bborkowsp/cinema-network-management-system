package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;

import java.util.List;

@Builder
public record ScreeningResponse(
        MovieResponse movie,
        List<ScreeningTimeResponse> screeningTimes
) {
}
