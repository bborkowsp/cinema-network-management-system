package org.example.cinemabackend.ticketing.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.ticketing.core.domain.Ticket;
import org.example.cinemabackend.ticketing.core.port.secondary.TicketRepository;
import org.example.cinemabackend.ticketing.infrastructure.schema.TicketSchema;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class TicketDatabaseGateway implements TicketRepository {
    private final TicketJpaRepository ticketJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public Ticket findByOrderId(String token) {
        return ticketJpaRepository.findByOrderId(token).get().toTicket();
    }

    @Override
    @Transactional
    public void save(Ticket ticket) {
        final var ticketSchema = TicketSchema.fromTicket(ticket);
        ticketJpaRepository.save(ticketSchema);
    }
}
