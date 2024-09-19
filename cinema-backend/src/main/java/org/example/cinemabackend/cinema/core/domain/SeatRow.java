package org.example.cinemabackend.cinema.core.domain;

import java.util.List;

public class SeatRow {
    private Long id;
    private List<Seat> seats;

    public SeatRow(Long id, List<Seat> seats) {
        this.id = id;
        this.seats = seats;
    }

    public SeatRow(List<Seat> seats) {
        this.seats = seats;
    }

    public SeatRow() {
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
