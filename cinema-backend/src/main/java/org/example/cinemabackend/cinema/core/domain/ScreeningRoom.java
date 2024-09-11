package org.example.cinemabackend.cinema.core.domain;

import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ScreeningRoom {
    private Long id;
    private String name;
    private List<SeatRow> seatRows;
    private Set<ProjectionTechnology> supportedTechnologies;
    private List<Screening> repertory;

    public ScreeningRoom(String name, List<SeatRow> seatRows, Set<ProjectionTechnology> supportedTechnologies, List<Screening> repertory) {
        this.name = name;
        this.seatRows = seatRows;
        this.supportedTechnologies = supportedTechnologies;
        this.repertory = repertory;
    }

    public ScreeningRoom(String name, List<SeatRow> seatRows, Set<ProjectionTechnology> supportedTechnologies) {
        this.name = name;
        this.seatRows = seatRows;
        this.supportedTechnologies = supportedTechnologies;
    }

    public ScreeningRoom(Long id, String name, List<SeatRow> seatRows, Set<ProjectionTechnology> supportedTechnologies, List<Screening> repertory) {
        this.id = id;
        this.name = name;
        this.seatRows = seatRows;
        this.supportedTechnologies = supportedTechnologies;
        this.repertory = repertory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProjectionTechnology> getSupportedTechnologies() {
        return supportedTechnologies;
    }

    public void setSupportedTechnologies(Set<ProjectionTechnology> supportedTechnologies) {
        this.supportedTechnologies = supportedTechnologies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SeatRow> getSeatRows() {
        return seatRows;
    }

    public List<Screening> getRepertory() {
        return repertory;
    }

    public void setRepertory(List<Screening> repertory) {
        this.repertory = repertory;
    }

    public void addScreening(Screening newScreening) {
        this.repertory.add(newScreening);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
