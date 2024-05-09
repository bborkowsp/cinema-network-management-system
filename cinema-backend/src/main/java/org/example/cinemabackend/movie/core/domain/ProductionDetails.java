package org.example.cinemabackend.movie.core.domain;

import java.time.LocalDate;
import java.util.Set;

public class ProductionDetails {
    private Long id;
    private LocalDate worldPremiereDate;
    private FilmMember director;
    private Set<FilmMember> actors;
    private Set<String> originalLanguages;
    private Set<String> productionCountries;

    public ProductionDetails(LocalDate worldPremiereDate, FilmMember director, Set<FilmMember> actors, Set<String> originalLanguages, Set<String> productionCountries) {
        this.worldPremiereDate = worldPremiereDate;
        this.director = director;
        this.actors = actors;
        this.originalLanguages = originalLanguages;
        this.productionCountries = productionCountries;
    }

    public ProductionDetails(Long id, LocalDate worldPremiereDate, FilmMember director, Set<FilmMember> actors, Set<String> originalLanguages, Set<String> productionCountries) {
        this.id = id;
        this.worldPremiereDate = worldPremiereDate;
        this.director = director;
        this.actors = actors;
        this.originalLanguages = originalLanguages;
        this.productionCountries = productionCountries;
    }

    public LocalDate getWorldPremiereDate() {
        return worldPremiereDate;
    }

    public void setWorldPremiereDate(LocalDate worldPremiereDate) {
        this.worldPremiereDate = worldPremiereDate;
    }

    public FilmMember getDirector() {
        return director;
    }

    public void setDirector(FilmMember director) {
        this.director = director;
    }

    public Set<FilmMember> getActors() {
        return actors;
    }

    public void setActors(Set<FilmMember> actors) {
        this.actors = actors;
    }

    public Set<String> getOriginalLanguages() {
        return originalLanguages;
    }

    public void setOriginalLanguages(Set<String> originalLanguages) {
        this.originalLanguages = originalLanguages;
    }

    public Set<String> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(Set<String> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
