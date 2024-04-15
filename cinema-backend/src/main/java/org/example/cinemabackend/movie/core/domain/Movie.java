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
    //    private Set<Image> images;
//    private Set<VideoFile> trailers;
    private Image poster;
    private VideoFile trailer;
    private Set<Genre> genres;
    private Set<ProjectionTechnology> projectionTechnologies;

    public Movie(String title, String originalTitle, Double duration, LocalDate releaseDate, ProductionDetails productionDetails, Description description, SubtitleAndSoundOptions subtitleAndSoundOptions, AgeRestriction ageRestriction, Image poster, VideoFile trailer, Set<Genre> genres, Set<ProjectionTechnology> projectionTechnologies) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.productionDetails = productionDetails;
        this.description = description;
        this.subtitleAndSoundOptions = subtitleAndSoundOptions;
        this.ageRestriction = ageRestriction;
        this.poster = poster;
        this.trailer = trailer;
        this.genres = genres;
        this.projectionTechnologies = projectionTechnologies;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public Double getDuration() {
        return duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public ProductionDetails getProductionDetails() {
        return productionDetails;
    }

    public Description getDescription() {
        return description;
    }

    public SubtitleAndSoundOptions getSubtitleAndSoundOptions() {
        return subtitleAndSoundOptions;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

//    public Set<Image> getImages() {
//        return images;
//    }
//
//    public Set<VideoFile> getTrailers() {
//        return trailers;
//    }

    public Image getPoster() {
        return poster;
    }

    public VideoFile getTrailer() {
        return trailer;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Set<ProjectionTechnology> getProjectionTechnologies() {
        return projectionTechnologies;
    }
}
