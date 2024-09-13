package org.example.cinemabackend.ticketing.core.port.secondary;

import org.example.cinemabackend.ticketing.core.domain.Ticket;

import java.util.Optional;

public interface TicketRepository {
    Optional<Ticket> findByOrderId(String token);

    void save(Ticket ticket);
}
