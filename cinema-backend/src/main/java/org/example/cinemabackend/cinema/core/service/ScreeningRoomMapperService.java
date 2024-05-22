package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateScreeningRoomRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateScreeningRoomRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningRoomResponse;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.primary.ProjectionTechnologyMapper;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningRoomMapper;
import org.example.cinemabackend.cinema.core.port.primary.SeatMapper;
import org.example.cinemabackend.cinema.core.port.primary.SeatRowMapper;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class ScreeningRoomMapperService implements ScreeningRoomMapper {

    private final SeatMapper seatMapper;
    private final SeatRowMapper seatRowMapper;
    private final ProjectionTechnologyMapper projectionTechnologyMapper;
    private final ProjectionTechnologyRepository projectionTechnologyRepository;

    @Override
    public Set<ScreeningRoom> mapCreateScreeningRoomToScreeningRoom(Set<CreateScreeningRoomRequest> createScreeningRoomRequests) {
        return createScreeningRoomRequests.stream().map(this::mapCreateScreeningRoomToScreeningRoom).collect(Collectors.toSet());
    }

    @Override
    public ScreeningRoom mapCreateScreeningRoomToScreeningRoom(CreateScreeningRoomRequest createScreeningRoomRequest) {
        final var supportedTechnologies = createScreeningRoomRequest.supportedTechnologies().stream()
                .map(projectionTechnologyResponse -> projectionTechnologyRepository.findByTechnology(projectionTechnologyResponse.technology()).orElseThrow())
                .collect(Collectors.toSet());


        return new ScreeningRoom(
                createScreeningRoomRequest.name(),
                seatMapper.mapCreateSeatToSeatGrid(createScreeningRoomRequest.seats()),
                supportedTechnologies
        );
    }

    @Override
    public ScreeningRoomResponse mapScreeningRoomToScreeningRoomResponse(ScreeningRoom screeningRoom) {
        return ScreeningRoomResponse.builder()
                .name(screeningRoom.getName())
                .seatRows(screeningRoom.getSeatRows().stream().map(seatRowMapper::mapSeatRowToSeatRowResponse).toList())
                .supportedTechnologies(projectionTechnologyMapper.mapProjectionTechnologiesToProjectionTechnologyResponses(screeningRoom.getSupportedTechnologies()))
                .build();
    }

    @Override
    public Set<ScreeningRoomResponse> mapScreeningRoomToScreeningRoomResponses(Set<ScreeningRoom> screeningRooms) {
        return screeningRooms.stream().map(this::mapScreeningRoomToScreeningRoomResponse).collect(Collectors.toSet());
    }

    @Override
    public ScreeningRoom mapUpdateScreeningRoomToScreeningRoom(UpdateScreeningRoomRequest updateScreeningRoomRequest, ScreeningRoom screeningRoom) {
        final var supportedTechnologies = updateScreeningRoomRequest.supportedTechnologies().stream()
                .map(projectionTechnologyResponse -> projectionTechnologyRepository.findByTechnology(projectionTechnologyResponse.technology()).orElseThrow())
                .collect(Collectors.toSet());

        screeningRoom.setName(updateScreeningRoomRequest.name());
        screeningRoom.setSupportedTechnologies(supportedTechnologies);

        return screeningRoom;
    }

    @Override
    public Set<ScreeningRoom> mapUpdateScreeningRoomToScreeningRoom(Set<UpdateScreeningRoomRequest> updateScreeningRoomRequests, Set<ScreeningRoom> screeningRooms) {
        return updateScreeningRoomRequests.stream()
                .map(updateScreeningRoomRequest -> mapUpdateScreeningRoomToScreeningRoom(updateScreeningRoomRequest, screeningRooms.stream()
                        .filter(screeningRoom -> screeningRoom.getName().equals(updateScreeningRoomRequest.name()))
                        .findFirst()
                        .orElseThrow()))
                .collect(Collectors.toSet());
    }
}
