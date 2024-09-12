package org.example.cinemabackend.ticketing.core.port.secondary;

import org.example.cinemabackend.ticketing.core.domain.Ticket;

public interface TicketRepository {
    Ticket findByOrderId(String token);

    void save(Ticket ticket);
}
