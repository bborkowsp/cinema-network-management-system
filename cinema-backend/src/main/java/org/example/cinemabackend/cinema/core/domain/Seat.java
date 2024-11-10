package org.example.cinemabackend.cinema.core.domain;

import java.util.List;

public class Seat {
    private Long id;
    private Integer seatRow;
    private Integer seatColumn;
    private SeatZone seatZone;
    private List<SeatStatus> status;

    public Seat(Integer seatRow, Integer seatColumn, SeatZone seatZone, List<SeatStatus> status) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.seatZone = seatZone;
        this.status = status;
    }

    public Seat(Long id, Integer seatRow, Integer seatColumn, SeatZone seatZone, List<SeatStatus> status) {
        this.id = id;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.seatZone = seatZone;
        this.status = status;
    }

    public Integer getSeatRow() {
        return seatRow;
    }

    public Integer getSeatColumn() {
        return seatColumn;
    }

    public SeatZone getSeatZone() {
        return seatZone;
    }

    public List<SeatStatus> getSeatStatus() {
        return status;
    }

    public void setSeatStatus(List<SeatStatus> status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void removeSeatStatus(SeatStatus seatStatus) {
        status.remove(seatStatus);
    }
}
