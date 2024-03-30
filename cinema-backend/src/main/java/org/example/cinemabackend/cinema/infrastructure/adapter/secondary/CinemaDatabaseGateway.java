package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.infrastructure.schema.CinemaSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<Cinema> findAll(Pageable pageable) {
        return cinemaJpaRepository.findAll(pageable).map(CinemaSchema::toCinema);
    }

    @Override
    public boolean existsByName(String name) {
        return cinemaJpaRepository.existsByName(name);
    }
}
