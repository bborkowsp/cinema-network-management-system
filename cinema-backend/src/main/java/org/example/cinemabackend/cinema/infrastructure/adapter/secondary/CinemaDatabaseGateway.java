package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.infrastructure.schema.CinemaSchema;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class CinemaDatabaseGateway implements CinemaRepository {

    private final CinemaJpaRepository cinemaJpaRepository;

    @Override
    public Cinema save(Cinema cinema) {
        return this.cinemaJpaRepository.save(CinemaSchema.fromCinema(cinema)).toCinema();
    }

    @Override
    public Optional<Cinema> findByName(String name) {
        return cinemaJpaRepository.findByName(name).map(CinemaSchema::toCinema);
    }

    @Override
    public List<Cinema> findAll() {
        return cinemaJpaRepository.findAll().stream().map(CinemaSchema::toCinema).toList();
    }
}
