package org.example.cinemabackend.cinema.core.port.secondary;

import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.user.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface CinemaRepository {
    Optional<Cinema> findByName(String name);

    void save(Cinema cinema);

    List<Cinema> findAll();

    boolean existsByName(String name);

    void deleteByName(String name);

    Cinema findByCinemaManager(User user);

    Cinema findByUserEmail(String email);

    void updateCinemaManager(Long cinemaId, Long cinemaManagerId);
}
