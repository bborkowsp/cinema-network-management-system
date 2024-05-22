package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.response.SeatRowResponse;
import org.example.cinemabackend.cinema.core.domain.SeatRow;
import org.example.cinemabackend.cinema.core.port.primary.SeatMapper;
import org.example.cinemabackend.cinema.core.port.primary.SeatRowMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SeatRowMapperService implements SeatRowMapper {

    private final SeatMapper seatMapper;

    @Override
    public SeatRowResponse mapSeatRowToSeatRowResponse(SeatRow seatRow) {
        return SeatRowResponse.builder()
                .columnSeats(seatRow.getColumnSeats().stream().map(seatMapper::mapSeatToSeatResponse).toList())
                .build();
    }
}
