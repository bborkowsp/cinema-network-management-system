package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import org.example.cinemabackend.cinema.infrastructure.schema.CinemaSchema;
import org.example.cinemabackend.user.infrastructure.scheme.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaJpaRepository extends JpaRepository<CinemaSchema, Long> {
    Optional<CinemaSchema> findByName(String name);

    boolean existsByName(String name);

    void deleteByName(String name);

    Optional<CinemaSchema> findByCinemaManager(UserSchema user);

    Optional<CinemaSchema> findByCinemaManagerEmail(String email);

}
