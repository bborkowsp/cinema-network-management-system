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

    public FilmMember getDirector() {
        return director;
    }

    public Set<FilmMember> getActors() {
        return actors;
    }

    public Set<String> getOriginalLanguages() {
        return originalLanguages;
    }

    public Set<String> getProductionCountries() {
        return productionCountries;
    }

    public Long getId() {
        return id;
    }
}
