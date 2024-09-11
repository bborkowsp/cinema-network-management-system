package org.example.cinemabackend.cinema.core.service;

import org.example.cinemabackend.cinema.application.dto.request.create.CreatSeatRequest;
import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.domain.SeatRow;
import org.example.cinemabackend.cinema.core.port.primary.SeatMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class SeatMapperService implements SeatMapper {

    @Override
    public SeatResponse[][] mapSeatRowsToSeatResponses(List<SeatRow> seatRows) {
        SeatResponse[][] seatResponses = new SeatResponse[seatRows.size()][seatRows.getFirst().getSeats().size()];
        for (int i = 0; i < seatRows.size(); i++) {
            for (int j = 0; j < seatRows.get(i).getSeats().size(); j++) {
                seatResponses[i][j] = mapSeatToSeatResponse(seatRows.get(i).getSeats().get(j));
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
    public Set<Seat> mapSeatResponsesToSeat(Set<SeatResponse> seatResponses) {
        return seatResponses.stream()
                .map(this::mapSeatResponseToSeat)
                .collect(Collectors.toSet());
    }

    @Override
    public Seat mapSeatResponseToSeat(SeatResponse seatResponse) {
        return new Seat(
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
                seat.getSeatRow(),
                seat.getSeatColumn(),
                seat.getSeatZone(),
                seat.getSeatStatus()
        );
    }

    @Override
    public Seat[][] mapCreateSeatRequestToSeat(CreatSeatRequest[][] seats) {
        return mapCreateSeatToSeats(seats);
    }

    @Override
    public SeatResponse[][] mapSeatToSeatResponses(Seat[][] seatingPlan) {
        SeatResponse[][] seatResponses = new SeatResponse[seatingPlan.length][seatingPlan[0].length];
        for (int i = 0; i < seatingPlan.length; i++) {
            for (int j = 0; j < seatingPlan[i].length; j++) {
                seatResponses[i][j] = mapSeatToSeatResponse(seatingPlan[i][j]);
            }
        }
        return seatResponses;
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
