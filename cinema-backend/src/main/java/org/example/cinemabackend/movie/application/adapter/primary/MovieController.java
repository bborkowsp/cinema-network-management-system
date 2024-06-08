package org.example.cinemabackend.movie.application.adapter.primary;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.movie.application.dto.request.CreateMovieRequest;
import org.example.cinemabackend.movie.application.dto.request.UpdateMovieRequest;
import org.example.cinemabackend.movie.application.dto.response.MovieListResponse;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;
import org.example.cinemabackend.movie.core.domain.AgeRestriction;
import org.example.cinemabackend.movie.core.domain.Genre;
import org.example.cinemabackend.movie.core.port.primary.MovieUseCases;
import org.example.cinemabackend.shared.dto.ResponseList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/movies")
@RequiredArgsConstructor
class MovieController {
    private final MovieUseCases movieUseCases;

    @GetMapping
    ResponseEntity<Page<MovieListResponse>> getMovies(Pageable pageable) {
        final var movies = movieUseCases.getMovies(pageable);
        return ResponseEntity.ok(movies);
    }

    @GetMapping({"/titles"})
    ResponseEntity<ResponseList<String>> getMovieTitles() {
        final var movieTitles = movieUseCases.getMovieTitles();
        return ResponseEntity.ok(new ResponseList<>(movieTitles));
    }

    @GetMapping("/genres")
    ResponseEntity<List<Genre>> getGenres() {
        final var genres = movieUseCases.getGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/age-restrictions")
    ResponseEntity<List<AgeRestriction>> getAgeRestrictions() {
        final var ageRestrictions = movieUseCases.getAgeRestrictions();
        return ResponseEntity.ok(ageRestrictions);
    }

    @GetMapping("/{title}")
    ResponseEntity<MovieResponse> getMovie(@PathVariable String title) {
        final var cinema = movieUseCases.getMovie(title);
        return ResponseEntity.ok(cinema);
    }

    @PostMapping
    ResponseEntity<Void> createMovie(@RequestBody @Valid CreateMovieRequest createMovieRequest) {
        movieUseCases.createMovie(createMovieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{title}")
    ResponseEntity<Void> updateMovie(@PathVariable String title, @RequestBody @Valid UpdateMovieRequest updateMovieRequest) {
        movieUseCases.updateMovie(title, updateMovieRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{title}")
    ResponseEntity<Void> deleteMovie(@PathVariable String title) {
        movieUseCases.deleteMovie(title);
        return ResponseEntity.noContent().build();
    }
}
