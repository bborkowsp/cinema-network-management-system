package org.example.cinemabackend.cinema.application.dto.response;

import org.example.cinemabackend.movie.application.dto.response.MovieResponse;

import java.util.List;
import java.util.Map;

public record ScreeningDetailsResponse(
        MovieResponse movie,
        Map<String, List<ScreeningResponse>> screenings
) {
}
