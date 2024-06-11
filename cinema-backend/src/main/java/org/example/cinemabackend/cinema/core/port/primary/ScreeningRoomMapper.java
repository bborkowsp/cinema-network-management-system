package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateScreeningRoomRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateScreeningRoomRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningRoomResponse;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;

import java.util.Set;

public interface ScreeningRoomMapper {
    Set<ScreeningRoom> mapCreateScreeningRoomToScreeningRoom(Set<CreateScreeningRoomRequest> createScreeningRoomRequests);

    ScreeningRoom mapCreateScreeningRoomToScreeningRoom(CreateScreeningRoomRequest createScreeningRoomRequest);

    ScreeningRoomResponse mapScreeningRoomToScreeningRoomResponse(ScreeningRoom screeningRoom);

    Set<ScreeningRoomResponse> mapScreeningRoomToScreeningRoomResponses(Set<ScreeningRoom> screeningRooms);

    ScreeningRoom mapUpdateScreeningRoomToScreeningRoom(UpdateScreeningRoomRequest updateScreeningRoomRequest, ScreeningRoom screeningRoom);

    Set<ScreeningRoom> mapUpdateScreeningRoomToScreeningRoom(Set<UpdateScreeningRoomRequest> updateScreeningRoomRequests);
}
