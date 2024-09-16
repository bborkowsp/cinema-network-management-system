package org.example.cinemabackend.movie.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.movie.core.domain.Language;
import org.example.cinemabackend.movie.core.domain.MovieVariant;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnologyEnum;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieVariantSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectionTechnologyEnum projectionTechnologyEnum;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;

    public static MovieVariantSchema fromMovieVariant(MovieVariant movieVariant) {
        return MovieVariantSchema.builder()
                .id(movieVariant.getId())
                .projectionTechnologyEnum(movieVariant.getProjectionTechnologyEnum())
                .language(movieVariant.getLanguage())
                .build();
    }

    public MovieVariant toMovieVariant() {
        return new MovieVariant(id, projectionTechnologyEnum, language);
    }
}
