package org.example.cinemabackend.movie.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.movie.core.domain.Movie;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.example.cinemabackend.movie.infrastructure.schema.MovieSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class MovieDatabaseGateway implements MovieRepository {

    private final MovieJpaRepository movieJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> findByTitle(String title) {
        return this.movieJpaRepository.findByTitle(title).map(MovieSchema::toMovie);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Movie> findAll(Pageable pageable) {
        return this.movieJpaRepository.findAll(pageable).map(MovieSchema::toMovie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return this.movieJpaRepository.findAll().stream().map(MovieSchema::toMovie).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean findByProjectionTechnology(String technology) {
        return this.movieJpaRepository.existsByProjectionTechnologyTechnology(technology);
    }

    @Override
    @Transactional
    public void save(Movie movie) {
        final var movieSchema = MovieSchema.fromMovie(movie);
        this.movieJpaRepository.save(movieSchema);
    }

    @Override
    @Transactional
    public void deleteByTitle(String title) {
        this.movieJpaRepository.deleteByTitle(title);
    }
}
