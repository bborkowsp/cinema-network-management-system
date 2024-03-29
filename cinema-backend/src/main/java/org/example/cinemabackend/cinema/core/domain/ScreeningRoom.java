package org.example.cinemabackend.cinema.core.domain;

import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;

import java.util.Set;

public class ScreeningRoom {
    private String screeningRoomName;
    private Set<Seat> seats;
    private Set<ProjectionTechnology> supportedTechnologies;

    public ScreeningRoom(String screeningRoomName, Set<Seat> seats, Set<ProjectionTechnology> supportedTechnologies) {
        this.screeningRoomName = screeningRoomName;
        this.seats = seats;
        this.supportedTechnologies = supportedTechnologies;
    }

    public String getScreeningRoomName() {
        return screeningRoomName;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public Set<ProjectionTechnology> getSupportedTechnologies() {
        return supportedTechnologies;
    }
}
