package org.example.cinemabackend.movie.core.domain;

import java.time.LocalDate;
import java.util.Set;

public class Movie {
    private Long id;
    private String title;
    private String originalTitle;
    private Integer duration;
    private LocalDate releaseDate;
    private ProductionDetails productionDetails;
    private String description;
    private AgeRestriction ageRestriction;
    private String poster;
    private String trailer;
    private Set<Genre> genres;
    private Set<MovieVariant> movieVariants;

    public Movie(Long id, String title, String originalTitle, Integer duration, LocalDate releaseDate, ProductionDetails productionDetails, String description, AgeRestriction ageRestriction, String trailer, Set<Genre> genres, Set<MovieVariant> movieVariants) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.productionDetails = productionDetails;
        this.description = description;
        this.ageRestriction = ageRestriction;
        this.trailer = trailer;
        this.genres = genres;
        this.movieVariants = movieVariants;
    }

    public Movie(String title, String originalTitle, Integer duration, LocalDate releaseDate, ProductionDetails productionDetails, String description, AgeRestriction ageRestriction, String trailer, Set<Genre> genres, Set<MovieVariant> movieVariants) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.productionDetails = productionDetails;
        this.description = description;
        this.ageRestriction = ageRestriction;
        this.trailer = trailer;
        this.genres = genres;
        this.movieVariants = movieVariants;
    }

    public Movie(Long id, String title, String originalTitle, Integer duration, LocalDate releaseDate, ProductionDetails productionDetails, String description, AgeRestriction ageRestriction, String poster, String trailer, Set<Genre> genres, Set<MovieVariant> movieVariants) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.productionDetails = productionDetails;
        this.description = description;
        this.ageRestriction = ageRestriction;
        this.poster = poster;
        this.trailer = trailer;
        this.genres = genres;
        this.movieVariants = movieVariants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
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

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<MovieVariant> getMovieVariants() {
        return movieVariants;
    }

    public void setMovieVariants(Set<MovieVariant> movieVariants) {
        this.movieVariants = movieVariants;
    }
}
