package org.example.cinemabackend.cinema.core.port.secondary;

import org.example.cinemabackend.cinema.core.domain.Cinema;

import java.util.List;
import java.util.Optional;

public interface CinemaRepository {
    Optional<Cinema> findByName(String name);

    Cinema save(Cinema cinema);

    List<Cinema> findAll();

    boolean existsByName(String name);
}
