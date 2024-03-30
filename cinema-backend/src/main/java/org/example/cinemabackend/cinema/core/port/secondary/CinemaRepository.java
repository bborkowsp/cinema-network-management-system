package org.example.cinemabackend.cinema.core.port.secondary;

import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CinemaRepository {
    Optional<Cinema> findByName(String name);

    Cinema save(Cinema cinema);

    Page<Cinema> findAll(Pageable pageable);

    boolean existsByName(String name);
}
