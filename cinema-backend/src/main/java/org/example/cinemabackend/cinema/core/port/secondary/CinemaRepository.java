package org.example.cinemabackend.cinema.core.port.secondary;

import org.example.cinemabackend.cinema.core.domain.Cinema;

import java.util.Optional;

public interface CinemaRepository {
    Optional<Cinema> findByName(String name);
}
