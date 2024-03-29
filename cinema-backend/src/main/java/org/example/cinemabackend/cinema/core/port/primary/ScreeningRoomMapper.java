package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.CreateScreeningRoomRequest;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;

import java.util.Set;

public interface ScreeningRoomMapper {
    Set<ScreeningRoom> mapCreateScreeningRoomToScreeningRoom(Set<CreateScreeningRoomRequest> createScreeningRoomRequests);

    ScreeningRoom mapCreateScreeningRoomToScreeningRoom(CreateScreeningRoomRequest createScreeningRoomRequest);
}
