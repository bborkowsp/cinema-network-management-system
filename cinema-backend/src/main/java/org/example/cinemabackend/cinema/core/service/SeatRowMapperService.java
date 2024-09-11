package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreatSeatRequest;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.domain.SeatRow;
import org.example.cinemabackend.cinema.core.port.primary.SeatMapper;
import org.example.cinemabackend.cinema.core.port.primary.SeatRowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class SeatRowMapperService implements SeatRowMapper {
    private final SeatMapper seatMapper;

    @Override
    public List<SeatRow> mapCreateSeatRowToSeatRow(CreatSeatRequest[][] creatSeatRequests) {
        List<SeatRow> seatRows = new ArrayList<>();
        for (CreatSeatRequest[] creatSeatRequestArray : creatSeatRequests) {
            List<Seat> seats = new ArrayList<>();
            for (CreatSeatRequest seatRequest : creatSeatRequestArray) {
                seats.add(seatMapper.mapCreateSeatRequestToSeat(seatRequest));
            }
            SeatRow seatRow = new SeatRow();
            seatRow.setSeats(seats);
            seatRows.add(seatRow);
        }
        return seatRows;
    }
}
