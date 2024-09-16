package org.example.cinemabackend.movie.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.movie.core.domain.AgeRestriction;
import org.example.cinemabackend.movie.core.domain.Genre;
import org.example.cinemabackend.movie.core.domain.Movie;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(name = "original_title", nullable = false)
    private String originalTitle;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String poster;

    @Column(nullable = false)
    private String trailer;

    @Column(nullable = false)
    private AgeRestriction ageRestriction;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "movie_genres")
    private Set<Genre> genres;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true, fetch = FetchType.EAGER)
    private ProductionDetailsSchema productionDetails;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MovieVariantSchema> movieVariants;

    public static MovieSchema fromMovie(Movie movie) {
        return MovieSchema.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .originalTitle(movie.getOriginalTitle())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .productionDetails(ProductionDetailsSchema.fromProductionDetails(movie.getProductionDetails()))
                .description(movie.getDescription())
                .poster(movie.getPoster())
                .ageRestriction(movie.getAgeRestriction())
                .trailer(movie.getTrailer())
                .genres(movie.getGenres())
                .movieVariants(movie.getMovieVariants().stream().map(MovieVariantSchema::fromMovieVariant).collect(Collectors.toSet()))
                .build();
    }

    public Movie toMovie() {
        return new Movie(
                this.id,
                this.title,
                this.originalTitle,
                this.duration,
                this.releaseDate,
                this.productionDetails.toProductionDetails(),
                this.description,
                this.ageRestriction,
                this.poster,
                this.trailer,
                this.genres,
                this.movieVariants.stream().map(MovieVariantSchema::toMovieVariant).collect(java.util.stream.Collectors.toSet())
        );
    }
}
