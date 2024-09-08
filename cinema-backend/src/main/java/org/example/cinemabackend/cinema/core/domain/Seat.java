package org.example.cinemabackend.cinema.core.domain;

public class Seat {
    private Long id;
    private Integer seatRow;
    private Integer seatColumn;
    private SeatZone seatZone;
    private SeatStatus seatStatus;

    public Seat(Integer seatRow, Integer seatColumn, SeatZone seatZone, SeatStatus seatStatus) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.seatZone = seatZone;
        this.seatStatus = seatStatus;
    }

    public Seat(Long id, Integer seatRow, Integer seatColumn, SeatZone seatZone, SeatStatus seatStatus) {
        this.id = id;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.seatZone = seatZone;
        this.seatStatus = seatStatus;
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

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
