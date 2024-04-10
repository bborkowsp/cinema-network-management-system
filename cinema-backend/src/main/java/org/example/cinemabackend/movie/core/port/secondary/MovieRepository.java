package org.example.cinemabackend.movie.core.port.secondary;

import org.example.cinemabackend.movie.core.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MovieRepository {

    Optional<Movie> findByTitle(String title);

    Page<Movie> findAll(Pageable pageable);

    void save(Movie movie);

    void deleteByTitle(String title);

    boolean findByProjectionTechnology(String technology);
}
