package org.example.cinemabackend.movie.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MovieDatabaseGateway implements MovieRepository {

    private final MovieJpaRepository movieJpaRepository;

    @Override
    public boolean findByProjectionTechnology(String technology) {
        return this.movieJpaRepository.existsByProjectionTechnologyTechnology(technology);
    }
}
