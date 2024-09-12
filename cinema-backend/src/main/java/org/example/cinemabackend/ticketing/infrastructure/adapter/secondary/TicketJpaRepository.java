package org.example.cinemabackend.ticketing.infrastructure.adapter.secondary;

import org.example.cinemabackend.ticketing.infrastructure.schema.TicketSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketJpaRepository extends JpaRepository<TicketSchema, Long> {
    Optional<TicketSchema> findByOrderId(String orderId);
}
