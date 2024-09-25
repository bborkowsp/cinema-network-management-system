package org.example.cinemabackend.ticketing.core.port.primary;

import org.example.cinemabackend.ticketing.application.dto.response.TicketResponse;
import org.example.cinemabackend.ticketing.core.domain.Ticket;

import java.util.List;

public interface TicketMapper {
    List<TicketResponse> mapTicketsToTicketResponses(List<Ticket> tickets);

    TicketResponse mapTicketToTicketResponse(Ticket ticket);
}
