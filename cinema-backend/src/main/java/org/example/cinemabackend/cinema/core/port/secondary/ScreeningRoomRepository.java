package org.example.cinemabackend.cinema.core.port.secondary;

import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;

import java.util.Optional;

public interface ScreeningRoomRepository {

    Optional<ScreeningRoom> findByName(String screeningRoomName);
}
