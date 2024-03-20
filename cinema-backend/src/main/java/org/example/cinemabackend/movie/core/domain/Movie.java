package org.example.cinemabackend.movie.core.domain;

import java.time.LocalDate;
import java.util.Set;

public class Movie extends AbstractEntity<Long> {
    private String title;
    private String originalTitle;
    private Double duration;
    private LocalDate releaseDate;
    private ProductionDetails productionDetails;
    private Description description;
    private SubtitleAndSoundOptions subtitleAndSoundOptions;
    private AgeRestriction ageRestriction;
    private VideoFile movieFile;
    private Set<Image> images;
    private Set<VideoFile> trailers;
    private Set<Genre> genres;
    private Set<ProjectionTechnology> projectionTechnologies;

    public Movie(String title, String originalTitle, Double duration, LocalDate releaseDate, ProductionDetails productionDetails, Description description, SubtitleAndSoundOptions subtitleAndSoundOptions, AgeRestriction ageRestriction, VideoFile movieFile, Set<Image> images, Set<VideoFile> trailers, Set<Genre> genres, Set<ProjectionTechnology> projectionTechnologies) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.productionDetails = productionDetails;
        this.description = description;
        this.subtitleAndSoundOptions = subtitleAndSoundOptions;
        this.ageRestriction = ageRestriction;
        this.movieFile = movieFile;
        this.images = images;
        this.trailers = trailers;
        this.genres = genres;
        this.projectionTechnologies = projectionTechnologies;
    }
}
