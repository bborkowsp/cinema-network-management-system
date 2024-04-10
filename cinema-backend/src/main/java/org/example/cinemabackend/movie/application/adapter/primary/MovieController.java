package org.example.cinemabackend.movie.application.adapter.primary;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.movie.application.dto.request.CreateMovieRequest;
import org.example.cinemabackend.movie.application.dto.response.MovieListResponse;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;
import org.example.cinemabackend.movie.core.port.primary.MovieUseCases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/movies")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class MovieController {
    private final MovieUseCases movieUseCases;

    @GetMapping
    ResponseEntity<Page<MovieListResponse>> getCinemas(Pageable pageable) {
        final var movies = movieUseCases.getMovies(pageable);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{name}")
    ResponseEntity<MovieResponse> getCinema(@PathVariable String title) {
        final var cinema = movieUseCases.getMovie(title);
        return ResponseEntity.ok(cinema);
    }

    @PostMapping
    ResponseEntity<Void> createCinema(@RequestBody @Valid CreateMovieRequest createMovieRequest) {
        movieUseCases.createMovie(createMovieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{name}")
    ResponseEntity<Void> deleteCinema(@PathVariable String title) {
        movieUseCases.deleteMovie(title);
        return ResponseEntity.noContent().build();
    }
}
