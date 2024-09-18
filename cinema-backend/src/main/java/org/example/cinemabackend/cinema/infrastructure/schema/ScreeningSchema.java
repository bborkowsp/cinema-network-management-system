package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.movie.infrastructure.schema.MovieSchema;
import org.example.cinemabackend.movie.infrastructure.schema.MovieVariantSchema;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScreeningSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private MovieSchema movie;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MovieVariantSchema movieVariant;

    public static ScreeningSchema fromScreening(Screening screening) {
        return ScreeningSchema.builder()
                .id(screening.getId())
                .movie(MovieSchema.fromMovie(screening.getMovie()))
                .startTime(screening.getStartTime())
                .endTime(screening.getEndTime())
                .movieVariant(MovieVariantSchema.fromMovieVariant(screening.getMovieVariant()))
                .build();
    }

    public Screening toScreening() {
        return new Screening(
                this.id,
                movie.toMovie(),
                startTime,
                endTime,
                movieVariant.toMovieVariant()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, startTime, endTime, movieVariant);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreeningSchema that = (ScreeningSchema) o;
        return Objects.equals(id, that.id) && Objects.equals(movie, that.movie) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(movieVariant, that.movieVariant);
    }
}
