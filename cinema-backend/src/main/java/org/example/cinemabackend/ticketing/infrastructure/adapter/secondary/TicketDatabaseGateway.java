package org.example.cinemabackend.ticketing.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.ticketing.core.port.secondary.TicketRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TicketDatabaseGateway implements TicketRepository {
}
