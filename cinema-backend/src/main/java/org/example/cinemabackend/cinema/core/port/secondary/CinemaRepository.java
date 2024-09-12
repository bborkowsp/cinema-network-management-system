package org.example.cinemabackend.cinema.core.port.secondary;

import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.user.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface CinemaRepository {

    Optional<Cinema> findByScreeningRoom(ScreeningRoom screeningRoom);

    List<Cinema> findAll();

    List<String> findAllCinemaNames();

    Optional<Cinema> findByName(String name);

    Optional<Cinema> findByUserEmail(String email);

    Optional<Cinema> findByCinemaManager(User user);

    boolean existsByName(String name);

    boolean existsByCinemaManagerEmail(String email);

    void save(Cinema cinema);

    void updateCinemaManager(Cinema cinemaId, Long cinemaManagerId);

    void deleteByName(String name);

}
