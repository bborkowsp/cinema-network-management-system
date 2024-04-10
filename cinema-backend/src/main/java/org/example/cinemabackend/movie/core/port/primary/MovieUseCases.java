package org.example.cinemabackend.movie.core.port.primary;

import org.example.cinemabackend.movie.application.dto.request.CreateMovieRequest;
import org.example.cinemabackend.movie.application.dto.response.MovieListResponse;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieUseCases {
    Page<MovieListResponse> getMovies(Pageable pageable);

    MovieResponse getMovie(String title);

    void createMovie(CreateMovieRequest createMovieRequest);

    void deleteMovie(String title);
}
