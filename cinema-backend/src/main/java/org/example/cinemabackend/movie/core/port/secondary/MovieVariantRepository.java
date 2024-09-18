package org.example.cinemabackend.movie.core.port.secondary;

import org.example.cinemabackend.movie.core.domain.MovieVariant;

import java.util.Optional;

public interface MovieVariantRepository {
    Optional<MovieVariant> findById(Long id);
}
