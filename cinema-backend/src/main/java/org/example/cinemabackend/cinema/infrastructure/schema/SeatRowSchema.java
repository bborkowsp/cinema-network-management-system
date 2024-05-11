package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.SeatRow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<SeatSchema> columnSeats = new ArrayList<>();


    public static SeatRowSchema fromSeatRow(SeatRow seatRowSchema) {
        return SeatRowSchema.builder()
                .id(seatRowSchema.getId())
                .columnSeats(seatRowSchema.getColumnSeats().stream().map(SeatSchema::fromSeat).collect(Collectors.toList()))
                .build();
    }

    public SeatRow toSeatRow() {
        return new SeatRow(
                this.id,
                this.columnSeats.stream().map(SeatSchema::toSeat).collect(Collectors.toList())
        );
    }
}
