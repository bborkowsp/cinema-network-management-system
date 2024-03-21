package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.infrastructure.config.AbstractEntitySchema;
import org.example.cinemabackend.movie.infrastructure.schema.MovieSchema;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScreeningSchema extends AbstractEntitySchema<Long> {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private MovieSchema movie;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ScreeningTimeScheme> screeningTimes = new ArrayList<>();

    public static ScreeningSchema fromScreening(Screening screening) {
        return ScreeningSchema.builder()
                .movie(MovieSchema.fromMovie(screening.getMovie()))
                .screeningTimes(screening.getScreeningTimes().stream().map(ScreeningTimeScheme::fromScreeningTime).collect(java.util.stream.Collectors.toList()))
                .build();
    }

    public Screening toScreening() {
        return new Screening(
                movie.toMovie(),
                screeningTimes.stream().map(ScreeningTimeScheme::toScreeningTime).collect(java.util.stream.Collectors.toList())
        );
    }
}
