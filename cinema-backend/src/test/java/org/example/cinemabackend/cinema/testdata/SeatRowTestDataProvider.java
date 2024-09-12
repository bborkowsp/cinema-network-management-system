package org.example.cinemabackend.cinema.testdata;

import org.example.cinemabackend.cinema.core.domain.SeatRow;

import java.util.ArrayList;
import java.util.List;

import static org.example.cinemabackend.cinema.testdata.SeatTestDataProvider.generateSeats;

public class SeatRowTestDataProvider {
    private static final int NUMBER_OF_SEAT_ROWS = 5;

    public static List<SeatRow> generateSeatRows() {
        List<SeatRow> seatRows = new ArrayList<>();
        for (int row = 0; row < NUMBER_OF_SEAT_ROWS; row++) {
            seatRows.add(createSeatRow(row));
        }
        return seatRows;
    }

    private static SeatRow createSeatRow(int row) {
        return new SeatRow(generateSeats(row));
    }
}
