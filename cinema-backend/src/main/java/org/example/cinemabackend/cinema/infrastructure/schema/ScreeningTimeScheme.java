package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.ScreeningTime;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScreeningTimeScheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private ScreeningRoomSchema screeningRoom;

    @Column(nullable = false)
    private LocalDateTime time;

    public static ScreeningTimeScheme fromScreeningTime(ScreeningTime screeningTime) {
        return ScreeningTimeScheme.builder()
                .id(screeningTime.getId())
                .screeningRoom(ScreeningRoomSchema.fromScreeningRoom(screeningTime.getScreeningRoom()))
                .time(screeningTime.getTime())
                .build();
    }

    public ScreeningTime toScreeningTime() {
        return new ScreeningTime(
                this.id,
                screeningRoom.toScreeningRoom(),
                this.time
        );
    }
}
