package org.example.cinemabackend.movie.core.port.primary;

import org.example.cinemabackend.movie.application.dto.request.CreateMovieRequest;
import org.example.cinemabackend.movie.application.dto.request.UpdateMovieRequest;
import org.example.cinemabackend.movie.application.dto.response.MovieListResponse;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;
import org.example.cinemabackend.movie.core.domain.Movie;

public interface MovieMapper {
    MovieListResponse mapMovieToMovieListResponse(Movie movie);

    MovieResponse mapMovieToMovieResponse(Movie movie);

    Movie mapCreateMovieRequestToMovie(CreateMovieRequest createMovieRequest);

    void updateMovieFromUpdateMovieRequest(UpdateMovieRequest updateMovieRequest, Movie movie);
}
