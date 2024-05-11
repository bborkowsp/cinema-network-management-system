package org.example.cinemabackend.cinema.core.service;

import org.example.cinemabackend.cinema.application.dto.request.CreatSeatRequest;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.domain.SeatRow;
import org.example.cinemabackend.cinema.core.port.primary.SeatMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class SeatMapperService implements SeatMapper {


    @Override
    public Seat mapCreateSeatToSeat(CreatSeatRequest seat) {
        return new Seat(
                seat.rowNumber(),
                seat.columnNumber(),
                seat.seatZone(),
                seat.seatType()
        );
    }

    @Override
    public List<SeatRow> mapCreateSeatToSeatGrid(CreatSeatRequest[][] creatSeatRequest) {
        Seat[][] seats = mapCreateSeatToSeats(creatSeatRequest);

        List<SeatRow> seatRows = new ArrayList<>();
        for (Seat[] seat : seats) {
            SeatRow seatRow = new SeatRow();
            for (Seat value : seat) {
                seatRow.getColumnSeats().add(value);
            }
            seatRows.add(seatRow);
        }
        return seatRows;
    }

    private Seat[][] mapCreateSeatToSeats(CreatSeatRequest[][] creatSeatRequest) {
        Seat[][] seats = new Seat[creatSeatRequest.length][creatSeatRequest[0].length];
        for (int i = 0; i < creatSeatRequest.length; i++) {
            for (int j = 0; j < creatSeatRequest[i].length; j++) {
                seats[i][j] = mapCreateSeatToSeat(creatSeatRequest[i][j]);
            }
        }
        return seats;
    }
}
