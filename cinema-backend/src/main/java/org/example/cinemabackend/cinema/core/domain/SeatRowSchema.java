package org.example.cinemabackend.cinema.core.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.infrastructure.schema.SeatSchema;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatRowSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SeatSchema> seats;

    public static SeatRowSchema fromSeatRow(SeatRow seatRow) {
        return SeatRowSchema.builder()
                .seats(seatRow.getSeats().stream().map(SeatSchema::fromSeat).toList())
                .build();
    }

    public SeatRow toSeatRow() {
        return new SeatRow(
                this.id,
                this.seats.stream().map(SeatSchema::toSeat).toList()
        );
    }
}
