package org.example.cinemabackend.cinema.core.domain;

import org.example.cinemabackend.movie.core.domain.Movie;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Set;

public class Screening {
    private Movie movie;
    private Map<ScreeningRoom, Set<SimpleDateFormat>> screeningTimes;
}
