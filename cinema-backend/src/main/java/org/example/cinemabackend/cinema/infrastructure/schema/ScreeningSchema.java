package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.movie.infrastructure.schema.MovieSchema;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ScreeningTimeScheme> screeningTimes = new ArrayList<>();

    public static ScreeningSchema fromScreening(Screening screening) {
        return ScreeningSchema.builder()
                .id(screening.getId())
                .movie(MovieSchema.fromMovie(screening.getMovie()))
                .screeningTimes(screening.getScreeningTimes().stream().map(ScreeningTimeScheme::fromScreeningTime).collect(java.util.stream.Collectors.toList()))
                .build();
    }

    public Screening toScreening() {
        return new Screening(
                this.id,
                movie.toMovie(),
                screeningTimes.stream().map(ScreeningTimeScheme::toScreeningTime).collect(java.util.stream.Collectors.toList())
        );
    }
}
