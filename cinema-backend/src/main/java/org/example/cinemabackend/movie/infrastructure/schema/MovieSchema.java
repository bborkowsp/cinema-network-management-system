package org.example.cinemabackend.movie.infrastructure.schema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.cinemabackend.cinema.infrastructure.config.AbstractEntitySchema;
import org.example.cinemabackend.cinema.infrastructure.schema.ImageSchema;
import org.example.cinemabackend.cinema.infrastructure.schema.ProjectionTechnologySchema;
import org.example.cinemabackend.movie.core.domain.AgeRestriction;
import org.example.cinemabackend.movie.core.domain.Genre;
import org.example.cinemabackend.movie.core.domain.Movie;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieSchema extends AbstractEntitySchema<Long> {

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String originalTitle;

    @Column(nullable = false)
    private Double duration;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private ProductionDetailsSchema productionDetails;

    @Embedded
    @NotNull
    private DescriptionSchema description;

    @Embedded
    @NotNull
    private SubtitleAndSoundOptionsSchema subtitleAndSoundOptions;

    @Column(nullable = false)
    private AgeRestriction ageRestriction;

    @Embedded
    @NotNull
    private VideoFileSchema movieFile;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<ImageSchema> images;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<VideoFileSchema> trailers;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "movie_genres")
    private Set<Genre> genres;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectionTechnologySchema> projectionTechnologies;

    public static MovieSchema fromMovie(Movie movie) {
        return MovieSchema.builder()
                .title(movie.getTitle())
                .originalTitle(movie.getOriginalTitle())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .productionDetails(ProductionDetailsSchema.fromProductionDetails(movie.getProductionDetails()))
                .description(DescriptionSchema.fromDescription(movie.getDescription()))
                .subtitleAndSoundOptions(SubtitleAndSoundOptionsSchema.fromSubtitleAndSoundOptions(movie.getSubtitleAndSoundOptions()))
                .ageRestriction(movie.getAgeRestriction())
                .movieFile(VideoFileSchema.fromVideoFile(movie.getMovieFile()))
                .images(movie.getImages().stream().map(ImageSchema::fromImage).collect(Collectors.toSet()))
                .trailers(movie.getTrailers().stream().map(VideoFileSchema::fromVideoFile).collect(Collectors.toSet()))
                .genres(movie.getGenres())
                .projectionTechnologies(movie.getProjectionTechnologies().stream().map(ProjectionTechnologySchema::fromProjectionTechnology).collect(Collectors.toSet()))
                .build();
    }

    public Movie toMovie() {
        Movie movie = new Movie(
                this.title,
                this.originalTitle,
                this.duration,
                this.releaseDate,
                this.productionDetails.toProductionDetails(),
                this.description.toDescription(),
                this.subtitleAndSoundOptions.toSubtitleAndSoundOptions(),
                this.ageRestriction,
                this.movieFile.toVideoFile(),
                this.images.stream().map(ImageSchema::toImage).collect(Collectors.toSet()),
                this.trailers.stream().map(VideoFileSchema::toVideoFile).collect(Collectors.toSet()),
                this.genres,
                this.projectionTechnologies.stream().map(ProjectionTechnologySchema::toProjectionTechnology).collect(Collectors.toSet())
        );
        movie.setId(this.getId());
        return movie;
    }
}
