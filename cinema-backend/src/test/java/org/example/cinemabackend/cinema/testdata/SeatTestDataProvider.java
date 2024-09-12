package org.example.cinemabackend.cinema.testdata;

import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.domain.SeatStatus;
import org.example.cinemabackend.cinema.core.domain.SeatZone;

import java.util.ArrayList;
import java.util.List;

public class SeatTestDataProvider {
    private static final int NUMBER_OF_SEATS_PER_ROW = 4;

    public static List<Seat> generateSeats(int row) {
        List<Seat> seatRows = new ArrayList<>();
        for (int column = 0; column < NUMBER_OF_SEATS_PER_ROW; column++) {
            seatRows.add(createSeat(row, column));
        }
        return seatRows;
    }

    private static Seat createSeat(int row, int column) {
        return new Seat(
                row,
                column,
                SeatZone.STANDARD,
                SeatStatus.AVAILABLE
        );
    }
}
