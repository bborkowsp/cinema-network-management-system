package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import org.example.cinemabackend.cinema.infrastructure.schema.CinemaSchema;
import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningRoomSchema;
import org.example.cinemabackend.user.infrastructure.schema.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaJpaRepository extends JpaRepository<CinemaSchema, Long> {

    @Query("SELECT cinema.name FROM CinemaSchema cinema")
    List<String> findAllCinemaNames();

    Optional<CinemaSchema> findByScreeningRoomsContains(ScreeningRoomSchema screeningRoomSchema);

    Optional<CinemaSchema> findByName(String name);

    Optional<CinemaSchema> findByCinemaManager(UserSchema user);

    Optional<CinemaSchema> findByCinemaManagerEmail(String email);

    boolean existsByName(String name);

    boolean existsByCinemaManagerEmail(String email);

    @Modifying
    @Query("update CinemaSchema cinema set cinema.cinemaManager.id = :cinemaManagerId where cinema.id = :cinemaId")
    void updateCinemaManager(Long cinemaId, Long cinemaManagerId);

    @Modifying
    @Query("update CinemaSchema cinema set cinema.cinemaManager = null where cinema.id = :cinemaId")
    void updateCinemaManagerToNull(Long cinemaId);

    void deleteByName(String name);
}
