package org.example.cinemabackend.movie.infrastructure.adapter.secondary;

import org.example.cinemabackend.movie.infrastructure.scheme.MovieScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieJpaRepository extends JpaRepository<MovieScheme, Long> {
}
