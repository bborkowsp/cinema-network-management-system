package org.example.cinemabackend.cinema.core.service;

import org.example.cinemabackend.cinema.application.dto.request.create.CreatSeatRequest;
import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.domain.SeatRow;
import org.example.cinemabackend.cinema.core.port.primary.SeatMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
class SeatMapperService implements SeatMapper {

    @Override
    public SeatResponse[][] mapSeatRowsToSeatResponses(List<SeatRow> seatRows) {
        SeatResponse[][] seatResponses = new SeatResponse[seatRows.size()][seatRows.getFirst().getSeats().size()];
        for (int i = 0; i < seatRows.size(); i++) {
            List<Seat> modifiableSeats = new ArrayList<>(seatRows.get(i).getSeats());
            modifiableSeats.sort(Comparator.comparingInt(Seat::getSeatColumn));
            for (int j = 0; j < modifiableSeats.size(); j++) {
                seatResponses[i][j] = mapSeatToSeatResponse(modifiableSeats.get(j));
            }
        }
        return seatResponses;
    }

    @Override
    public Seat mapCreateSeatRequestToSeat(CreatSeatRequest seatRequest) {
        return new Seat(
                seatRequest.seatRow(),
                seatRequest.seatColumn(),
                seatRequest.seatZone(),
                seatRequest.seatStatus()
        );
    }

    @Override
    public List<Seat> mapSeatResponsesToSeat(List<SeatResponse> seatResponses) {
        return seatResponses.stream()
                .map(this::mapSeatResponseToSeat)
                .collect(Collectors.toList());
    }

    @Override
    public Seat mapSeatResponseToSeat(SeatResponse seatResponse) {
        return new Seat(
                seatResponse.id(),
                seatResponse.seatRow(),
                seatResponse.seatColumn(),
                seatResponse.seatZone(),
                seatResponse.seatStatus()
        );
    }

    @Override
    public Seat mapCreateSeatToSeat(CreatSeatRequest seat) {
        return new Seat(
                seat.seatRow(),
                seat.seatColumn(),
                seat.seatZone(),
                seat.seatStatus()
        );
    }

    @Override
    public SeatResponse mapSeatToSeatResponse(Seat seat) {
        return new SeatResponse(
                seat.getId(),
                seat.getSeatRow(),
                seat.getSeatColumn(),
                seat.getSeatZone(),
                seat.getSeatStatus()
        );
    }
}
