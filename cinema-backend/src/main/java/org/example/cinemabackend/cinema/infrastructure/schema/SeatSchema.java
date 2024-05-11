package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.domain.SeatType;
import org.example.cinemabackend.cinema.core.domain.SeatZone;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer seatRow;

    @Column(nullable = false)
    private Integer seatColumn;

    @Column(nullable = false)
    private SeatZone seatZone;

    @Column(nullable = false)
    private SeatType seatType;

    public static SeatSchema fromSeat(Seat seat) {
        return SeatSchema.builder()
                .id(seat.getId())
                .seatRow(seat.getSeatRow())
                .seatColumn(seat.getSeatColumn())
                .seatZone(seat.getSeatZone())
                .seatType(seat.getSeatType())
                .build();
    }

    public Seat toSeat() {
        return new Seat(
                this.id,
                this.seatRow,
                this.seatColumn,
                this.seatZone,
                this.seatType
        );
    }
}
