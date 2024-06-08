package org.example.cinemabackend.cinema.testdata;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreatSeatRequest;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateScreeningRoomRequest;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyNameResponse;
import org.example.cinemabackend.cinema.core.domain.SeatType;
import org.example.cinemabackend.cinema.core.domain.SeatZone;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.cinemabackend.cinema.testdata.ProjectionTechnologyTestDataProvider.generateProjectionTechnologies;

@Component
@RequiredArgsConstructor
public class ScreeningRoomTestDataProvider {

    private static final int NUMBER_OF_SCREENING_ROOMS = 6;
    private final ProjectionTechnologyRepository projectionTechnologyRepository;

    public Set<CreateScreeningRoomRequest> generateCreateScreeningRoomRequests() {
        saveProjectionTechnologiesToDatabase(generateProjectionTechnologies());

        Set<CreateScreeningRoomRequest> screeningRooms = new HashSet<>();
        while (screeningRooms.size() < NUMBER_OF_SCREENING_ROOMS) {
            screeningRooms.add(createScreeningRoom());
        }
        return screeningRooms;
    }

    private CreateScreeningRoomRequest createScreeningRoom() {
        final var seats = createSeats();
        final var projectionTechnologies = getProjectionTechnologiesNameResponses();
        return CreateScreeningRoomRequest.builder()
                .name("Create Screening Room Request")
                .seats(seats)
                .supportedTechnologies(projectionTechnologies)
                .build();
    }

    private CreatSeatRequest[][] createSeats() {
        final var rows = 3;
        final var columns = 6;
        CreatSeatRequest[][] seats = new CreatSeatRequest[rows][columns];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++)
                seats[i][j] = createSeat(i, j);
        }
        return seats;
    }

    private CreatSeatRequest createSeat(int seatRow, int seatColumn) {
        return new CreatSeatRequest(
                seatRow,
                seatColumn,
                SeatZone.CORRIDOR,
                SeatType.AVAILABLE
        );
    }

    private Set<ProjectionTechnologyNameResponse> getProjectionTechnologiesNameResponses() {
        final var projectionTechnologies = projectionTechnologyRepository.findAll();
        return projectionTechnologies.stream()
                .map(projectionTechnology -> ProjectionTechnologyNameResponse.builder()
                        .technology(projectionTechnology.getTechnology())
                        .build())
                .collect(Collectors.toSet());
    }


    private void saveProjectionTechnologiesToDatabase(List<ProjectionTechnology> projectionTechnologies) {
        projectionTechnologies.forEach(projectionTechnologyRepository::save);
    }
}
