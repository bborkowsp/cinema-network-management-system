package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningRoomSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScreeningRoomJpaRepository extends JpaRepository<ScreeningRoomSchema, Long> {
    Optional<ScreeningRoomSchema> findByName(String name);
}
