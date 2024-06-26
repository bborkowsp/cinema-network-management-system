package org.example.cinemabackend.movie.core.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRepository;
import org.example.cinemabackend.movie.application.dto.request.CreateMovieRequest;
import org.example.cinemabackend.movie.application.dto.request.UpdateMovieRequest;
import org.example.cinemabackend.movie.application.dto.response.MovieListResponse;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;
import org.example.cinemabackend.movie.core.domain.AgeRestriction;
import org.example.cinemabackend.movie.core.domain.Genre;
import org.example.cinemabackend.movie.core.domain.Movie;
import org.example.cinemabackend.movie.core.port.primary.MovieMapper;
import org.example.cinemabackend.movie.core.port.primary.MovieUseCases;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class MovieService implements MovieUseCases {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final ScreeningRepository screeningRepository;

    @Override
    public Page<MovieListResponse> getMovies(Pageable pageable) {
        return movieRepository.findAll(pageable).map(movieMapper::mapMovieToMovieListResponse);
    }

    @Override
    public List<Genre> getGenres() {
        return List.of(Genre.values());
    }

    @Override
    public List<AgeRestriction> getAgeRestrictions() {
        return List.of(AgeRestriction.values());
    }

    @Override
    public List<String> getMovieTitles() {
        final var movies = movieRepository.findAll();
        return movies.stream().map(Movie::getTitle).collect(Collectors.toList());
    }

    @Override
    public MovieResponse getMovie(String title) {
        final var movie = movieRepository.findByTitle(title).orElseThrow();
        return movieMapper.mapMovieToMovieResponse(movie);
    }

    @Override
    public void createMovie(CreateMovieRequest createMovieRequest) {
        validateMovieNotExists(createMovieRequest.title());
        final var movie = movieMapper.mapCreateMovieRequestToMovie(createMovieRequest);
        movieRepository.save(movie);
    }

    @Override
    public void updateMovie(String title, UpdateMovieRequest updateMovieRequest) {
        validateMovieTitleIsNotTaken(title, updateMovieRequest.title());
        final var movie = movieRepository.findByTitle(title).orElseThrow();
        movieMapper.updateMovieFromUpdateMovieRequest(updateMovieRequest, movie);
        movieRepository.save(movie);
    }

    @Override
    @Transactional
    public void deleteMovie(String title) {
        validateMovieExists(title);
        validateMovieIsNotUsedInScreenings(title);
        movieRepository.deleteByTitle(title);
    }

    private void validateMovieIsNotUsedInScreenings(String title) {
        if (screeningRepository.existsByMovieTitle(title)) {
            throw new IllegalArgumentException("Movie is used in screenings and cannot be yet deleted");
        }
    }

    private void validateMovieTitleIsNotTaken(String oldTitle, String newTitle) {
        if (!oldTitle.equals(newTitle) && movieRepository.findByTitle(newTitle).isPresent()) {
            throw new IllegalStateException("Movie with title " + newTitle + " already exists");
        }
    }

    private void validateMovieExists(String title) {
        if (movieRepository.findByTitle(title).isEmpty()) {
            throw new NoSuchElementException("Movie does not exist");
        }
    }

    private void validateMovieNotExists(String title) {
        if (movieRepository.findByTitle(title).isPresent()) {
            throw new IllegalStateException("Movie with title " + title + " already exists");
        }
    }
}
