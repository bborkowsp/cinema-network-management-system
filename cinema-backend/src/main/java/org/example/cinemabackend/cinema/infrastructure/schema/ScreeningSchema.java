package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.movie.infrastructure.schema.MovieSchema;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScreeningSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private MovieSchema movie;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private ScreeningRoomSchema screeningRoom;

    public static ScreeningSchema fromScreening(Screening screening) {
        return ScreeningSchema.builder()
                .id(screening.getId())
                .movie(MovieSchema.fromMovie(screening.getMovie()))
                .startTime(screening.getStartTime())
                .endTime(screening.getEndTime())
                .screeningRoom(ScreeningRoomSchema.fromScreeningRoom(screening.getScreeningRoom()))
                .build();
    }

    public Screening toScreening() {
        return new Screening(
                this.id,
                movie.toMovie(),
                startTime,
                endTime,
                screeningRoom.toScreeningRoom()
        );
    }
}
