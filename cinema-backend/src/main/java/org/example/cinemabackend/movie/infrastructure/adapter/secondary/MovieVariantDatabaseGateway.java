package org.example.cinemabackend.movie.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.movie.core.domain.MovieVariant;
import org.example.cinemabackend.movie.core.port.secondary.MovieVariantRepository;
import org.example.cinemabackend.movie.infrastructure.schema.MovieVariantSchema;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieVariantDatabaseGateway implements MovieVariantRepository {
    private final MovieVariantJpaRepository movieVariantJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<MovieVariant> findById(Long id) {
        return this.movieVariantJpaRepository.findById(id).map(MovieVariantSchema::toMovieVariant);
    }
}
