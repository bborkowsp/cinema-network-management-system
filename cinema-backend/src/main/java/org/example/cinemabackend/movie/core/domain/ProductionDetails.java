package org.example.cinemabackend.movie.core.domain;

import java.text.SimpleDateFormat;
import java.util.Set;

public class ProductionDetails {
    private SimpleDateFormat worldPremiereDate;
    private FilmMember director;
    private Set<FilmMember> actors;
    private Set<String> originalLanguages;
    private Set<String> productionCountries;
}
