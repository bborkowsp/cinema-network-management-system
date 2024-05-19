package org.example.cinemabackend.cinema.core.domain;

import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;

import java.util.List;
import java.util.Set;

public class ScreeningRoom {
    private Long id;
    private String name;
    private List<SeatRow> seatRows;
    private Set<ProjectionTechnology> supportedTechnologies;

    public ScreeningRoom(String name, List<SeatRow> seats, Set<ProjectionTechnology> supportedTechnologies) {
        this.name = name;
        this.seatRows = seats;
        this.supportedTechnologies = supportedTechnologies;
    }

    public ScreeningRoom(Long id, String name, List<SeatRow> seats, Set<ProjectionTechnology> supportedTechnologies) {
        this.id = id;
        this.name = name;
        this.seatRows = seats;
        this.supportedTechnologies = supportedTechnologies;
    }

    public String getName() {
        return name;
    }

    public Set<ProjectionTechnology> getSupportedTechnologies() {
        return supportedTechnologies;
    }

    public Long getId() {
        return id;
    }

    public List<SeatRow> getSeatRows() {
        return seatRows;
    }
}
