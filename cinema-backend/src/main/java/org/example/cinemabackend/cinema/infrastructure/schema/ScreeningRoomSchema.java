package org.example.cinemabackend.cinema.infrastructure.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.domain.Seat;

import java.util.HashSet;
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
    private String name;

    @Column(columnDefinition = "TEXT")
    private String seatingPlan;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<ProjectionTechnologySchema> supportedTechnologies = new HashSet<>();


    public static ScreeningRoomSchema fromScreeningRoom(ScreeningRoom screeningRoom) {
        String seatingPlanAsJson = convertSeatToSeatSchema(screeningRoom.getSeatingPlan());
        return ScreeningRoomSchema.builder()
                .id(screeningRoom.getId())
                .name(screeningRoom.getName())
                .seatingPlan(seatingPlanAsJson)
                .supportedTechnologies(screeningRoom.getSupportedTechnologies().stream().map(ProjectionTechnologySchema::fromProjectionTechnology).collect(Collectors.toSet()))
                .build();
    }

    private static String convertSeatToSeatSchema(Seat[][] seatingPlan) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(seatingPlan);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public ScreeningRoom toScreeningRoom() {
        SeatSchema[][] seatingPlanSchema = getSeatingPlanFromJson();
        Seat[][] seatingPlan = convertSeatSchemaToSeat(seatingPlanSchema);

        return new ScreeningRoom(
                this.id,
                this.name,
                seatingPlan,
                this.supportedTechnologies.stream().map(ProjectionTechnologySchema::toProjectionTechnology).collect(Collectors.toSet())
        );
    }

    private SeatSchema[][] getSeatingPlanFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(this.seatingPlan, SeatSchema[][].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Seat[][] convertSeatSchemaToSeat(SeatSchema[][] seatingPlan) {
        Seat[][] seats = new Seat[seatingPlan.length][seatingPlan[0].length];
        for (int i = 0; i < seatingPlan.length; i++) {
            for (int j = 0; j < seatingPlan[i].length; j++) {
                seats[i][j] = seatingPlan[i][j].toSeat();
            }
        }
        return seats;
    }


}
