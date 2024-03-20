package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.ScreeningTime;
import org.example.cinemabackend.cinema.infrastructure.config.AbstractEntitySchema;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScreeningTimeScheme extends AbstractEntitySchema<Long> {

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private ScreeningRoomSchema screeningRoom;

    @Column(nullable = false)
    private LocalDateTime time;

    public static ScreeningTimeScheme fromScreeningTime(ScreeningTime screeningTime) {
        return ScreeningTimeScheme.builder()
                .screeningRoom(ScreeningRoomSchema.fromScreeningRoom(screeningTime.getScreeningRoom()))
                .time(screeningTime.getTime())
                .build();
    }

    public ScreeningTime toScreeningTime() {
        return new ScreeningTime(
                screeningRoom.toScreeningRoom(),
                this.time
        );
    }
}
