package org.example.cinemabackend.ticketing.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.domain.SeatStatus;
import org.example.cinemabackend.cinema.core.port.primary.SeatMapper;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRoomRepository;
import org.example.cinemabackend.ticketing.application.dto.request.BuyTicketRequest;
import org.example.cinemabackend.ticketing.core.port.primary.TicketUseCases;
import org.example.cinemabackend.ticketing.core.port.secondary.TicketRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
@RequiredArgsConstructor
class TicketService implements TicketUseCases {
    private final SeatMapper seatMapper;
    private final TicketRepository ticketRepository;
    private final ScreeningRoomRepository screeningRoomRepository;
    private final ScreeningRepository screeningRepository;

    @Override
    public void changeSeatsStatusToReserved(Set<SeatResponse> seat, ScreeningRoom screeningRoom) {
        ;
        ;
    }

    @Override
    public BigDecimal getOrderFee(Set<SeatResponse> seatResponses) {
        BigDecimal fee = BigDecimal.ZERO;
        for (SeatResponse seatResponse : seatResponses) {
            BigDecimal price = seatResponse.seatZone().getPrice();
            fee = fee.add(price);
        }
        return fee;
    }

    @Override
    public ScreeningRoom validateSeatsAreAvailable(BuyTicketRequest seatResponses) {
        final var screening = screeningRepository.findById(seatResponses.movieId());
        final var screeningRoom = screeningRoomRepository.findByContainsScreening(screening.get());
        validateIfSeatIsAvailable(screeningRoom.get().getSeatingPlan());
        return screeningRoom.get();
    }

    private void validateIfSeatIsAvailable(Seat[][] seats) {
        for (Seat[] row : seats) {
            for (Seat seat : row) {
                if (seat.getSeatStatus() == SeatStatus.RESERVED || seat.getSeatStatus() == SeatStatus.UNAVAILABLE) {
                    throw new IllegalArgumentException("Seat cannot be reserved");
                }
            }
        }
    }
}
