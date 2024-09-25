package org.example.cinemabackend.ticketing.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.ticketing.core.domain.Ticket;
import org.example.cinemabackend.ticketing.core.port.secondary.TicketRepository;
import org.example.cinemabackend.ticketing.infrastructure.schema.TicketSchema;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class TicketDatabaseGateway implements TicketRepository {
    private final TicketJpaRepository ticketJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAllByEmail(String email) {
        return ticketJpaRepository.findAllByEmail(email).stream().map(TicketSchema::toTicket).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ticket> findByOrderId(String token) {
        return ticketJpaRepository.findByOrderId(token).map(TicketSchema::toTicket);
    }

    @Override
    @Transactional
    public void save(Ticket ticket) {
        final var ticketSchema = TicketSchema.fromTicket(ticket);
        ticketJpaRepository.save(ticketSchema);
    }
}
