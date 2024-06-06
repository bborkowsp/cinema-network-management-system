package org.example.cinemabackend.cinema.core.port.secondary;

import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.user.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface CinemaRepository {
    Optional<Cinema> findByName(String name);

    Optional<Cinema> findByUserEmail(String email);

    Optional<Cinema> findByCinemaManager(User user);

    Optional<Cinema> findByRepertoryContains(Screening screening);

    List<Cinema> findAll();

    void save(Cinema cinema);

    void updateCinemaManager(Long cinemaId, Long cinemaManagerId);

    void deleteByName(String name);

    boolean existsByName(String name);

    boolean existsByCinemaManagerEmail(String email);

}
