package org.example.cinemabackend.cinema.core.domain;

import java.util.List;
import java.util.Objects;

public class ScreeningRoom {
    private Long id;
    private String name;
    private List<SeatRow> seatRows;
    private List<Screening> repertory;

    public ScreeningRoom(String name, List<SeatRow> seatRows, List<Screening> repertory) {
        this.name = name;
        this.seatRows = seatRows;
        this.repertory = repertory;
    }

    public ScreeningRoom(String name, List<SeatRow> seatRows) {
        this.name = name;
        this.seatRows = seatRows;
    }

    public ScreeningRoom(Long id, String name, List<SeatRow> seatRows, List<Screening> repertory) {
        this.id = id;
        this.name = name;
        this.seatRows = seatRows;
        this.repertory = repertory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SeatRow> getSeatRows() {
        return seatRows;
    }

    public void setSeatRows(List<SeatRow> seatRows) {
        this.seatRows = seatRows;
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
