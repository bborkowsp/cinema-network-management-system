package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.CreateScreeningRoomRequest;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.primary.ProjectionTechnologyMapper;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningRoomMapper;
import org.example.cinemabackend.cinema.core.port.primary.SeatMapper;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class ScreeningRoomMapperService implements ScreeningRoomMapper {

    private final SeatMapper seatMapper;
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
}
