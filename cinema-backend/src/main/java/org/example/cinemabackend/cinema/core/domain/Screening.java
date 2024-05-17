package org.example.cinemabackend.cinema.core.domain;

import org.example.cinemabackend.movie.core.domain.Movie;

import java.time.LocalDateTime;

public class Screening {
    private Long id;
    private Movie movie;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ScreeningRoom screeningRoom;

    public Screening(Long id, Movie movie, LocalDateTime startTime, LocalDateTime endTime, ScreeningRoom screeningRoom) {
        this.id = id;
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
        this.screeningRoom = screeningRoom;
    }

    public Screening(Movie movie, LocalDateTime startTime, LocalDateTime endTime, ScreeningRoom screeningRoom) {
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
        this.screeningRoom = screeningRoom;
    }

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }


}
