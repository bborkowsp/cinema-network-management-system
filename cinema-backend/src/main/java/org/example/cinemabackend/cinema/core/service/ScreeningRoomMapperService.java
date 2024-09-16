package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateScreeningRoomRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateScreeningRoomRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningRoomResponse;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningRoomMapper;
import org.example.cinemabackend.cinema.core.port.primary.SeatMapper;
import org.example.cinemabackend.cinema.core.port.primary.SeatRowMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class ScreeningRoomMapperService implements ScreeningRoomMapper {
    private final SeatMapper seatMapper;
    private final SeatRowMapper seatRowMapper;

    @Override
    public Set<ScreeningRoom> mapCreateScreeningRoomToScreeningRoom(Set<CreateScreeningRoomRequest> createScreeningRoomRequests) {
        return createScreeningRoomRequests.stream().map(this::mapCreateScreeningRoomToScreeningRoom).collect(Collectors.toSet());
    }

    @Override
    public ScreeningRoom mapCreateScreeningRoomToScreeningRoom(CreateScreeningRoomRequest createScreeningRoomRequest) {
        return new ScreeningRoom(
                createScreeningRoomRequest.name(),
                seatRowMapper.mapCreateSeatRowToSeatRow(createScreeningRoomRequest.seats())
        );
    }

    @Override
    public ScreeningRoomResponse mapScreeningRoomToScreeningRoomResponse(ScreeningRoom screeningRoom) {
        return ScreeningRoomResponse.builder()
                .id(screeningRoom.getId())
                .name(screeningRoom.getName())
                .seats(seatMapper.mapSeatRowsToSeatResponses(screeningRoom.getSeatRows()))
                .build();
    }

    @Override
    public Set<ScreeningRoomResponse> mapScreeningRoomToScreeningRoomResponses(Set<ScreeningRoom> screeningRooms) {
        return screeningRooms.stream().map(this::mapScreeningRoomToScreeningRoomResponse).collect(Collectors.toSet());
    }

    @Override
    public ScreeningRoom mapUpdateScreeningRoomToScreeningRoom(UpdateScreeningRoomRequest updateScreeningRoomRequest, ScreeningRoom screeningRoom) {
        screeningRoom.setName(updateScreeningRoomRequest.name());
        return screeningRoom;
    }

    @Override
    public Set<ScreeningRoom> mapUpdateScreeningRoomToScreeningRoom(Set<UpdateScreeningRoomRequest> updateScreeningRoomRequests) {
        return null;
    }
}
