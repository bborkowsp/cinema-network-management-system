package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import org.example.cinemabackend.cinema.infrastructure.scheme.CinemaScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaJpaRepository extends JpaRepository<CinemaScheme, Long> {
    Optional<CinemaScheme> findByName(String name);
}
