package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.SeatStatus;
import org.example.cinemabackend.cinema.core.domain.Status;

import java.time.LocalDateTime;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatStatusSchema {
    
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime statusStart;

    @Column(nullable = false)
    private LocalDateTime statusEnd;


    public static SeatStatusSchema fromStatus(SeatStatus seatStatus) {
        return SeatStatusSchema.builder()
                .status(seatStatus.getStatus())
                .statusStart(seatStatus.getStatusStart())
                .statusEnd(seatStatus.getStatusEnd())
                .build();
    }

    public SeatStatus toStatus() {
        return new SeatStatus(
                this.status,
                this.statusStart,
                this.statusEnd
        );
    }
}
