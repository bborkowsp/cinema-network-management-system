package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.domain.SeatRowSchema;
import org.example.cinemabackend.projectiontechnology.infrastructure.schema.ProjectionTechnologySchema;

import java.util.*;
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
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SeatRowSchema> seatRows;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<ProjectionTechnologySchema> supportedTechnologies = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ScreeningSchema> repertory;

    public static ScreeningRoomSchema fromScreeningRoom(ScreeningRoom screeningRoom) {
        return ScreeningRoomSchema.builder()
                .id(screeningRoom.getId())
                .name(screeningRoom.getName())
                .seatRows(screeningRoom.getSeatRows().stream().map(SeatRowSchema::fromSeatRow).collect(Collectors.toList()))
                .supportedTechnologies(screeningRoom.getSupportedTechnologies().stream().map(ProjectionTechnologySchema::fromProjectionTechnology).collect(Collectors.toSet()))
                .repertory(screeningRoom.getRepertory() == null ? new ArrayList<>() : screeningRoom.getRepertory().stream().map(ScreeningSchema::fromScreening).collect(Collectors.toList()))
                .build();
    }

    public ScreeningRoom toScreeningRoom() {
        return new ScreeningRoom(
                this.id,
                this.name,
                this.seatRows.stream().map(SeatRowSchema::toSeatRow).collect(Collectors.toList()),
                this.supportedTechnologies.stream().map(ProjectionTechnologySchema::toProjectionTechnology).collect(Collectors.toSet()),
                this.repertory.stream().map(ScreeningSchema::toScreening).collect(Collectors.toList())
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof ScreeningRoomSchema screeningRoomSchema)) {
            return false;
        }

        return Objects.equals(id, screeningRoomSchema.getId()) &&
                Objects.equals(name, screeningRoomSchema.getName());
    }
}
