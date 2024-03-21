package org.example.cinemabackend.cinema.core.service;

import org.example.cinemabackend.cinema.application.dto.request.CreateScreeningRoomRequest;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningRoomMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
class ScreeningRoomMapperService implements ScreeningRoomMapper {

    @Override
    public Set<ScreeningRoom> mapCreateScreeningRoomToScreeningRoom(Set<CreateScreeningRoomRequest> createScreeningRoomRequests) {
        return null;
    }

    @Override
    public ScreeningRoom mapCreateScreeningRoomToScreeningRoom(CreateScreeningRoomRequest createScreeningRoomRequest) {
        return null;
    }
}
