package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.domain.SeatType;
import org.example.cinemabackend.cinema.core.domain.SeatZone;
import org.example.cinemabackend.cinema.infrastructure.config.AbstractEntitySchema;

@Data
@Entity
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatSchema extends AbstractEntitySchema<Long> {

    @Column(nullable = false, length = 50)
    private String seatNumber;

    @Column(nullable = false, length = 2)
    private String seatLetter;

    @Column(nullable = false)
    private SeatZone seatZone;

    @Column(nullable = false)
    private SeatType seatType;

    public static SeatSchema fromSeat(Seat seat) {
        return SeatSchema.builder()
                .seatNumber(seat.getSeatNumber())
                .seatLetter(seat.getSeatLetter())
                .seatZone(seat.getSeatZone())
                .seatType(seat.getSeatType())
                .build();
    }

    public Seat toSeat() {
        return new Seat(
                this.seatNumber,
                this.seatLetter,
                this.seatZone,
                this.seatType
        );
    }
}
