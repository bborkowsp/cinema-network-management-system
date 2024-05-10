package org.example.cinemabackend.movie.core.domain;

import java.time.LocalDate;
import java.util.Set;

public class Movie {
    private Long id;
    private String title;
    private String originalTitle;
    private Double duration;
    private LocalDate releaseDate;
    private ProductionDetails productionDetails;
    private String description;
    private SubtitleAndSoundOptions subtitleAndSoundOptions;
    private AgeRestriction ageRestriction;
    private Image poster;
    private VideoFile trailer;
    private Set<Genre> genres;
    private Set<ProjectionTechnology> projectionTechnologies;

    public Movie(String title, String originalTitle, Double duration, LocalDate releaseDate, ProductionDetails productionDetails, String description, SubtitleAndSoundOptions subtitleAndSoundOptions, AgeRestriction ageRestriction, Image poster, VideoFile trailer, Set<Genre> genres, Set<ProjectionTechnology> projectionTechnologies) {
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

    public Movie(Long id, String title, String originalTitle, Double duration, LocalDate releaseDate, ProductionDetails productionDetails, String description, SubtitleAndSoundOptions subtitleAndSoundOptions, AgeRestriction ageRestriction, Image poster, VideoFile trailer, Set<Genre> genres, Set<ProjectionTechnology> projectionTechnologies) {
        this.id = id;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ProductionDetails getProductionDetails() {
        return productionDetails;
    }

    public void setProductionDetails(ProductionDetails productionDetails) {
        this.productionDetails = productionDetails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SubtitleAndSoundOptions getSubtitleAndSoundOptions() {
        return subtitleAndSoundOptions;
    }

    public void setSubtitleAndSoundOptions(SubtitleAndSoundOptions subtitleAndSoundOptions) {
        this.subtitleAndSoundOptions = subtitleAndSoundOptions;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Image getPoster() {
        return poster;
    }

    public void setPoster(Image poster) {
        this.poster = poster;
    }

    public VideoFile getTrailer() {
        return trailer;
    }

    public void setTrailer(VideoFile trailer) {
        this.trailer = trailer;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<ProjectionTechnology> getProjectionTechnologies() {
        return projectionTechnologies;
    }

    public void setProjectionTechnologies(Set<ProjectionTechnology> projectionTechnologies) {
        this.projectionTechnologies = projectionTechnologies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
