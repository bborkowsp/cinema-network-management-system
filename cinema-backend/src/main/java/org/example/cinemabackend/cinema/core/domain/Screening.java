package org.example.cinemabackend.cinema.core.domain;

import org.example.cinemabackend.movie.core.domain.Movie;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }

    public void setScreeningRoom(ScreeningRoom screeningRoom) {
        this.screeningRoom = screeningRoom;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Screening screening)) {
            return false;
        }

        return Objects.equals(id, screening.id);
    }
}
