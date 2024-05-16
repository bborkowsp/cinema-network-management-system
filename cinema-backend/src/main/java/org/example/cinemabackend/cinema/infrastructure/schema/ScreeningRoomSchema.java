package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScreeningRoomSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String screeningRoomName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SeatRowSchema> seatRows = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<ProjectionTechnologySchema> supportedTechnologies = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private CinemaSchema cinema;


    public static ScreeningRoomSchema fromScreeningRoom(ScreeningRoom screeningRoom) {
        return ScreeningRoomSchema.builder()
                .id(screeningRoom.getId())
                .screeningRoomName(screeningRoom.getScreeningRoomName())
                .seatRows(screeningRoom.getSeatRows().stream().map(SeatRowSchema::fromSeatRow).collect(Collectors.toList()))
                .supportedTechnologies(screeningRoom.getSupportedTechnologies().stream().map(ProjectionTechnologySchema::fromProjectionTechnology).collect(Collectors.toSet()))
                .build();
    }

    public ScreeningRoom toScreeningRoom() {
        return new ScreeningRoom(
                this.id,
                this.screeningRoomName,
                this.seatRows.stream().map(SeatRowSchema::toSeatRow).collect(Collectors.toList()),
                this.supportedTechnologies.stream().map(ProjectionTechnologySchema::toProjectionTechnology).collect(Collectors.toSet())
        );
    }
}
