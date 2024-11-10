package org.example.cinemabackend.cinema.testdata;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreatSeatRequest;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateScreeningRoomRequest;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.domain.SeatZone;
import org.example.cinemabackend.cinema.core.domain.Status;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static org.example.cinemabackend.cinema.testdata.SeatRowTestDataProvider.generateSeatRows;

@Component
@RequiredArgsConstructor
public class ScreeningRoomTestDataProvider {
    private static final int NUMBER_OF_SCREENING_ROOMS = 6;
    private static final String SCREENING_ROOM_NAME = "Screening Room Name";
    private static final String CREATE_SCREENING_ROOM_NAME = "Create Screening Room Name";

    public Set<ScreeningRoom> generateScreeningRooms() {
        Set<ScreeningRoom> screeningRooms = new HashSet<>();
        for (int i = 0; i < NUMBER_OF_SCREENING_ROOMS; i++) {
            screeningRooms.add(createScreeningRoom(i));
        }
        return screeningRooms;
    }

    private ScreeningRoom createScreeningRoom(int i) {
        return new ScreeningRoom(
                SCREENING_ROOM_NAME + i,
                generateSeatRows()
        );
    }

    public Set<CreateScreeningRoomRequest> generateCreateScreeningRoomRequests() {
        Set<CreateScreeningRoomRequest> screeningRooms = new HashSet<>();
        for (int i = 0; i < NUMBER_OF_SCREENING_ROOMS; i++) {
            screeningRooms.add(createScreeningRoomRequest(i));
        }
        return screeningRooms;
    }

    private CreateScreeningRoomRequest createScreeningRoomRequest(int i) {
        final var seats = createSeats();
        return CreateScreeningRoomRequest.builder()
                .name(CREATE_SCREENING_ROOM_NAME + i)
                .seats(seats)
                .build();
    }

    private static CreatSeatRequest[][] createSeats() {
        final var rows = 3;
        final var columns = 6;
        CreatSeatRequest[][] seats = new CreatSeatRequest[rows][columns];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++)
                seats[i][j] = createSeat(i, j);
        }
        return seats;
    }

    private static CreatSeatRequest createSeat(int seatRow, int seatColumn) {
        return new CreatSeatRequest(
                seatRow,
                seatColumn,
                SeatZone.CORRIDOR,
                Status.AVAILABLE
        );
    }
}
