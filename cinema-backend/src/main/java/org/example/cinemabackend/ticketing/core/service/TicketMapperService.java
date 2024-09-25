package org.example.cinemabackend.ticketing.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningMapper;
import org.example.cinemabackend.cinema.core.port.primary.SeatMapper;
import org.example.cinemabackend.ticketing.application.dto.response.TicketResponse;
import org.example.cinemabackend.ticketing.core.domain.Ticket;
import org.example.cinemabackend.ticketing.core.port.primary.QrCodeUseCases;
import org.example.cinemabackend.ticketing.core.port.primary.TicketMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketMapperService implements TicketMapper {
    private final SeatMapper seatMapper;
    private final ScreeningMapper screeningMapper;
    private final QrCodeUseCases qrCodeUseCases;

    @Override
    public List<TicketResponse> mapTicketsToTicketResponses(List<Ticket> tickets) {
        return tickets.stream().map(this::mapTicketToTicketResponse).toList();
    }

    @Override
    public TicketResponse mapTicketToTicketResponse(Ticket ticket) {
        return TicketResponse.builder()
                .qrCode(qrCodeUseCases.encodeByteArrayToBase64(ticket.getQrCode()))
                .bookedSeats(seatMapper.mapSeatsToSeatResponses(ticket.getBookedSeats()))
                .screening(screeningMapper.mapScreeningToScreeningResponse(ticket.getScreening(), ticket.getScreeningRoom()))
                .cinemaName(ticket.getCinema().getName())
                .build();
    }
}
