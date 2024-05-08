package org.example.cinemabackend.cinema.core.domain;

import org.example.cinemabackend.movie.core.domain.Movie;

import java.util.List;

public class Screening {
    private Long id;
    private Movie movie;
    private List<ScreeningTime> screeningTimes;

    public Screening(Movie movie, List<ScreeningTime> screeningTimes) {
        this.movie = movie;
        this.screeningTimes = screeningTimes;
    }

    public Screening(Long id, Movie movie, List<ScreeningTime> screeningTimes) {
        this.id = id;
        this.movie = movie;
        this.screeningTimes = screeningTimes;
    }

    public Movie getMovie() {
        return movie;
    }

    public List<ScreeningTime> getScreeningTimes() {
        return screeningTimes;
    }

    public Long getId() {
        return id;
    }
}
