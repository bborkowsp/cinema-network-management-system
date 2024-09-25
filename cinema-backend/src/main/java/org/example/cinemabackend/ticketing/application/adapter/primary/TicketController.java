package org.example.cinemabackend.ticketing.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend._shared.dto.ResponseList;
import org.example.cinemabackend.ticketing.application.dto.response.TicketResponse;
import org.example.cinemabackend.ticketing.core.port.primary.TicketUseCases;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/tickets")
@RequiredArgsConstructor
class TicketController {
    private final TicketUseCases ticketUseCases;

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ResponseList<TicketResponse>> getTickets() {
        final var tickets = ticketUseCases.getTickets();
        return ResponseEntity.ok(new ResponseList<>(tickets));
    }
}
