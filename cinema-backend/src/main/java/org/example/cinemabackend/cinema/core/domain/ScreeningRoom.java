package org.example.cinemabackend.cinema.core.domain;

import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;

import java.util.Set;

public class ScreeningRoom {
    private String screeningRoomName;
    private Set<Seat> seats;
    private Set<ProjectionTechnology> supportedTechnologies;

}
