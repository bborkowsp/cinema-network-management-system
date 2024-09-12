package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.port.secondary.SeatRepository;
import org.example.cinemabackend.cinema.infrastructure.schema.SeatSchema;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatDatabaseGateway implements SeatRepository {
    private final SeatJpaRepository seatJpaRepository;

    @Override
    @Transactional
    public void save(Seat seat) {
        final var seatSchema = SeatSchema.fromSeat(seat);
        seatJpaRepository.save(seatSchema);
    }

    @Override
    @Transactional
    public Optional<Seat> findById(Long id) {
        return seatJpaRepository.findById(id).map(SeatSchema::toSeat);
    }
}
