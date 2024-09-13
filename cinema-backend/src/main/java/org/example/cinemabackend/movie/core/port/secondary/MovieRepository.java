package org.example.cinemabackend.movie.core.port.secondary;

import org.example.cinemabackend.movie.core.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

    Page<Movie> findAll(Pageable pageable);

    List<Movie> findAll();

    Optional<Movie> findByTitle(String title);

    boolean existsByProjectionTechnology(String technology);

    void save(Movie movie);

    void deleteByTitle(String title);
}
