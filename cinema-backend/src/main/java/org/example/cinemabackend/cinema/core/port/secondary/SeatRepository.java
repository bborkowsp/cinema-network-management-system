package org.example.cinemabackend.cinema.core.port.secondary;

import org.example.cinemabackend.cinema.core.domain.Seat;

import java.util.Optional;

public interface SeatRepository {
    void save(Seat seat);

    Optional<Seat> findById(Long id);
}
