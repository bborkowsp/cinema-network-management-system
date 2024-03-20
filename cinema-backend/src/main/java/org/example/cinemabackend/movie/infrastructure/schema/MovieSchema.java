package org.example.cinemabackend.movie.infrastructure.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.example.cinemabackend.cinema.infrastructure.config.AbstractEntitySchema;
import org.example.cinemabackend.movie.core.domain.Movie;

@Data
@Entity
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieSchema extends AbstractEntitySchema<Long> {

    @Column(nullable = false, unique = true)
    private String title;

    public static MovieSchema fromMovie(Movie movie) {
        return null;
    }

    public static Movie toMovie(MovieSchema movieSchema) {
        return movieSchema.toMovie();
    }

    public Movie toMovie() {
        return null;
    }
}
