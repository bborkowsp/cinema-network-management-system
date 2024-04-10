package org.example.cinemabackend.movie.core.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.movie.application.dto.request.CreateMovieRequest;
import org.example.cinemabackend.movie.application.dto.response.MovieListResponse;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;
import org.example.cinemabackend.movie.core.port.primary.MovieMapper;
import org.example.cinemabackend.movie.core.port.primary.MovieUseCases;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class MovieService implements MovieUseCases {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<MovieListResponse> getMovies(Pageable pageable) {
        return movieRepository.findAll(pageable).map(movieMapper::mapMovieToMovieListResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public MovieResponse getMovie(String title) {
        final var movie = movieRepository.findByTitle(title).orElseThrow();
        return movieMapper.mapMovieToMovieResponse(movie);
    }

    @Override
    @Transactional
    public void createMovie(CreateMovieRequest createMovieRequest) {
        validateMovieNotExists(createMovieRequest.title());
        final var movie = movieMapper.mapCreateMovieRequestToMovie(createMovieRequest);
        movieRepository.save(movie);
    }

    @Override
    @Transactional
    public void deleteMovie(String title) {
        validateMovieExists(title);
        movieRepository.deleteByTitle(title);
    }

    private void validateMovieExists(String title) {
        if (movieRepository.findByTitle(title).isEmpty()) {
            throw new IllegalArgumentException("Movie does not exist");
        }
    }

    private void validateMovieNotExists(String title) {
        if (movieRepository.findByTitle(title).isPresent()) {
            throw new IllegalArgumentException("Movie with title " + title + " already exists");
        }
    }

}
