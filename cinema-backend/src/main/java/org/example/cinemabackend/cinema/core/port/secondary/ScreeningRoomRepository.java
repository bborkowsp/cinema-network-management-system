package org.example.cinemabackend.cinema.core.port.secondary;

import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;

import java.util.Optional;

public interface ScreeningRoomRepository {

    Optional<ScreeningRoom> findByName(String screeningRoomName);

    void save(ScreeningRoom screeningRoom);

    Optional<ScreeningRoom> findByContainsScreening(Screening screening);

    Optional<ScreeningRoom> findByRepertoryContains(Screening screening);
}
