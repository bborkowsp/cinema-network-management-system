package org.example.cinemabackend.cinema.core.domain;

import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;

import java.util.Set;

public class ScreeningRoom {
    private Long id;
    private String name;
    private Seat[][] seatingPlan;
    private Set<ProjectionTechnology> supportedTechnologies;

    public ScreeningRoom(String name, Seat[][] seatingPlan, Set<ProjectionTechnology> supportedTechnologies) {
        this.name = name;
        this.seatingPlan = seatingPlan;
        this.supportedTechnologies = supportedTechnologies;
    }

    public ScreeningRoom(Long id, String name, Seat[][] seatingPlan, Set<ProjectionTechnology> supportedTechnologies) {
        this.id = id;
        this.name = name;
        this.seatingPlan = seatingPlan;
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

    public Seat[][] getSeatingPlan() {
        return seatingPlan;
    }

    public void setSupportedTechnologies(Set<ProjectionTechnology> supportedTechnologies) {
        this.supportedTechnologies = supportedTechnologies;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeatingPlan(Seat[][] seatingPlan) {
        this.seatingPlan = seatingPlan;
    }


}
