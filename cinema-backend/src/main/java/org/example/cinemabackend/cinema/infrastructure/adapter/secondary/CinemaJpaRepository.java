package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import org.example.cinemabackend.cinema.infrastructure.schema.CinemaSchema;
import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningSchema;
import org.example.cinemabackend.user.infrastructure.scheme.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CinemaJpaRepository extends JpaRepository<CinemaSchema, Long> {
    Optional<CinemaSchema> findByName(String name);

    Optional<CinemaSchema> findByCinemaManager(UserSchema user);

    Optional<CinemaSchema> findByCinemaManagerEmail(String email);

    Optional<CinemaSchema> findByRepertoryContains(ScreeningSchema screeningSchema);


    boolean existsByName(String name);

    boolean existsByCinemaManagerEmail(String email);

    void deleteByName(String name);

    @Modifying
    @Transactional
    @Query("update CinemaSchema c set c.cinemaManager.id = :cinemaManagerId where c.id = :cinemaId")
    void updateCinemaManager(Long cinemaId, Long cinemaManagerId);

    @Modifying
    @Transactional
    @Query("update CinemaSchema c set c.cinemaManager = null where c.id = :cinemaId")
    void updateCinemaManagerToNull(Long cinemaId);

}
