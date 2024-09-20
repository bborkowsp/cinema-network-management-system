package org.example.cinemabackend.movie.infrastructure.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.example.cinemabackend.movie.core.domain.Language;
import org.example.cinemabackend.movie.core.domain.MovieVariant;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieVariantSchema {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectionTechnology projectionTechnology;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;

    public static MovieVariantSchema fromMovieVariant(MovieVariant movieVariant) {
        return MovieVariantSchema.builder()
                .projectionTechnology(movieVariant.getProjectionTechnology())
                .language(movieVariant.getLanguage())
                .build();
    }

    public MovieVariant toMovieVariant() {
        return new MovieVariant(
                this.projectionTechnology,
                this.language
        );
    }
}
