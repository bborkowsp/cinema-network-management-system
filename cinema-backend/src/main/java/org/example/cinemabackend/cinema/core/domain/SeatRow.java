package org.example.cinemabackend.cinema.core.domain;

import java.util.ArrayList;
import java.util.List;

public class SeatRow {

    private Long id;

    private List<Seat> columnSeats = new ArrayList<>();

    public SeatRow() {
    }

    public SeatRow(List<Seat> columnSeats) {
        this.columnSeats = columnSeats;
    }

    public SeatRow(Long id, List<Seat> columnSeats) {
        this.id = id;
        this.columnSeats = columnSeats;
    }

    public List<Seat> getColumnSeats() {
        return columnSeats;
    }

    public void setColumnSeats(List<Seat> columnSeats) {
        this.columnSeats = columnSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
