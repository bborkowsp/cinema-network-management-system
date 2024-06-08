package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.infrastructure.schema.CinemaSchema;
import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningSchema;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.infrastructure.scheme.UserSchema;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class CinemaDatabaseGateway implements CinemaRepository {
    private final CinemaJpaRepository cinemaJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cinema> findAll() {
        return cinemaJpaRepository.findAll().stream().map(CinemaSchema::toCinema).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cinema> findByName(String name) {
        return cinemaJpaRepository.findByName(name).map(CinemaSchema::toCinema);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cinema> findByUserEmail(String email) {
        return cinemaJpaRepository.findByCinemaManagerEmail(email).map(CinemaSchema::toCinema);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cinema> findByCinemaManager(User user) {
        return cinemaJpaRepository.findByCinemaManager(UserSchema.fromUser(user)).map(CinemaSchema::toCinema);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cinema> findByRepertoryContains(Screening screening) {
        return cinemaJpaRepository.findByRepertoryContains(ScreeningSchema.fromScreening(screening)).map(CinemaSchema::toCinema);
    }

    @Override
    @Transactional
    public boolean existsByName(String name) {
        return cinemaJpaRepository.existsByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCinemaManagerEmail(@NonNull String email) {
        return this.cinemaJpaRepository.existsByCinemaManagerEmail(email);
    }

    @Override
    @Transactional
    public void save(Cinema cinema) {
        final var cinemaSchema = CinemaSchema.fromCinema(cinema);
        this.cinemaJpaRepository.save(cinemaSchema);
    }

    @Override
    @Transactional
    public void updateCinemaManager(Long cinemaId, Long cinemaManagerId) {
        this.cinemaJpaRepository.updateCinemaManager(cinemaId, cinemaManagerId);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        cinemaJpaRepository.deleteByName(name);
    }
}
