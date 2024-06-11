package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningSchema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningJpaRepository extends JpaRepository<ScreeningSchema, Long> {
    //   List<ScreeningSchema> findByScreeningRoomId(Long screeningRoomId);
}
