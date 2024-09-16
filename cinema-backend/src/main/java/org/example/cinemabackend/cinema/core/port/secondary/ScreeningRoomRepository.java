package org.example.cinemabackend.cinema.core.port.secondary;

import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;

import java.util.List;
import java.util.Optional;

public interface ScreeningRoomRepository {

    Optional<ScreeningRoom> findById(Long id);

    Optional<ScreeningRoom> findByScreeningId(Long id);

    Optional<ScreeningRoom> findByName(String screeningRoomName);

    void save(ScreeningRoom screeningRoom);

    Optional<ScreeningRoom> findByRepertoryContains(Screening screening);

    void saveAll(List<ScreeningRoom> updatedScreeningRooms);
}
