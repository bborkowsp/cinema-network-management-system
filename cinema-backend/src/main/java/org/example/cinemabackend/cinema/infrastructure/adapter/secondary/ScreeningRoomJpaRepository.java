package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningRoomSchema;
import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScreeningRoomJpaRepository extends JpaRepository<ScreeningRoomSchema, Long> {
    @Query("SELECT srs FROM ScreeningRoomSchema srs JOIN srs.repertory sr WHERE sr.id = :screeningId")
    Optional<ScreeningRoomSchema> findByRepertoryContainingScreeningId(@Param("screeningId") Long screeningId);

    Optional<ScreeningRoomSchema> findByName(String name);

    Optional<ScreeningRoomSchema> findByRepertoryContains(ScreeningSchema screeningSchema);
}
