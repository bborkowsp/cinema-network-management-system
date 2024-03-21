package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.infrastructure.config.AbstractEntitySchema;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScreeningRoomSchema extends AbstractEntitySchema<Long> {

    @Column(nullable = false, length = 50)
    private String screeningRoomName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<SeatSchema> seats = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ProjectionTechnologySchema> supportedTechnologies = new HashSet<>();
    ;

    public static ScreeningRoomSchema fromScreeningRoom(ScreeningRoom screeningRoom) {
        return ScreeningRoomSchema.builder()
                .screeningRoomName(screeningRoom.getScreeningRoomName())
                .seats(screeningRoom.getSeats().stream().map(SeatSchema::fromSeat).collect(Collectors.toSet()))
                .supportedTechnologies(screeningRoom.getSupportedTechnologies().stream().map(ProjectionTechnologySchema::fromProjectionTechnology).collect(Collectors.toSet()))
                .build();
    }

    public ScreeningRoom toScreeningRoom() {
        return new ScreeningRoom(
                this.screeningRoomName,
                this.seats.stream().map(SeatSchema::toSeat).collect(Collectors.toSet()),
                this.supportedTechnologies.stream().map(ProjectionTechnologySchema::toProjectionTechnology).collect(Collectors.toSet())
        );
    }
}
