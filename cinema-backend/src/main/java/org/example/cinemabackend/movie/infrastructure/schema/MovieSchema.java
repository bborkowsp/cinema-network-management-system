package org.example.cinemabackend.movie.infrastructure.schema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.cinemabackend.movie.core.domain.AgeRestriction;
import org.example.cinemabackend.movie.core.domain.Genre;
import org.example.cinemabackend.movie.core.domain.Movie;
import org.example.cinemabackend.projectiontechnology.infrastructure.schema.ProjectionTechnologySchema;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@AllArgsConstructor
@Builder
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

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true, fetch = FetchType.EAGER)
    private ProductionDetailsSchema productionDetails;

    @Column(nullable = false)
    private String description;

    @Embedded
    @NotNull
    private SubtitleAndSoundOptionsSchema subtitleAndSoundOptions;

    @Column(nullable = false)
    private AgeRestriction ageRestriction;

    @NotNull
    private String poster;

    @NotNull
    private String trailer;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "movie_genres")
    private Set<Genre> genres;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<ProjectionTechnologySchema> projectionTechnologies;

    public static MovieSchema fromMovie(Movie movie) {
        final var projectionTechnologies = movie.getProjectionTechnologies().stream()
                .map(ProjectionTechnologySchema::fromProjectionTechnology)
                .collect(Collectors.toSet());

        return MovieSchema.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .originalTitle(movie.getOriginalTitle())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .productionDetails(ProductionDetailsSchema.fromProductionDetails(movie.getProductionDetails()))
                .description(movie.getDescription())
                .poster(movie.getPoster())
                .subtitleAndSoundOptions(SubtitleAndSoundOptionsSchema.fromSubtitleAndSoundOptions(movie.getSubtitleAndSoundOptions()))
                .ageRestriction(movie.getAgeRestriction())
                .trailer(movie.getTrailer())
                .genres(movie.getGenres())
                .projectionTechnologies(projectionTechnologies)
                .build();
    }

    public Movie toMovie() {
        Movie movie = new Movie(
                this.id,
                this.title,
                this.originalTitle,
                this.duration,
                this.releaseDate,
                this.productionDetails.toProductionDetails(),
                this.description,
                this.subtitleAndSoundOptions.toSubtitleAndSoundOptions(),
                this.ageRestriction,
                this.trailer,
                this.poster,
                this.genres,
                this.projectionTechnologies.stream().map(ProjectionTechnologySchema::toProjectionTechnology).collect(Collectors.toSet())
        );
        return movie;
    }
}
