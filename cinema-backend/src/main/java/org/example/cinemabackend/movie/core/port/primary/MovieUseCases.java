package org.example.cinemabackend.movie.core.port.primary;

import org.example.cinemabackend.movie.application.dto.request.CreateMovieRequest;
import org.example.cinemabackend.movie.application.dto.request.UpdateMovieRequest;
import org.example.cinemabackend.movie.application.dto.response.MovieListResponse;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;
import org.example.cinemabackend.movie.core.domain.AgeRestriction;
import org.example.cinemabackend.movie.core.domain.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieUseCases {
    Page<MovieListResponse> getMovies(Pageable pageable);

    List<Genre> getGenres();

    List<AgeRestriction> getAgeRestrictions();

    List<String> getMovieTitles();

    MovieResponse getMovie(String title);

    void createMovie(MultipartFile image, CreateMovieRequest createMovieRequest);

    void updateMovie(String title, MultipartFile image, UpdateMovieRequest updateMovieRequest);

    void deleteMovie(String title);
}