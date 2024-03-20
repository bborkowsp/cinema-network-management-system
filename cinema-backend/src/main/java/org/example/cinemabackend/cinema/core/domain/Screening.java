package org.example.cinemabackend.cinema.core.domain;

import org.example.cinemabackend.movie.core.domain.Movie;

import java.util.List;

public class Screening {
    private Movie movie;
    private List<ScreeningTime> screeningTimes;

    public Screening(Movie movie, List<ScreeningTime> screeningTimes) {
        this.movie = movie;
        this.screeningTimes = screeningTimes;
    }

    public Movie getMovie() {
        return movie;
    }

    public List<ScreeningTime> getScreeningTimes() {
        return screeningTimes;
    }
}
