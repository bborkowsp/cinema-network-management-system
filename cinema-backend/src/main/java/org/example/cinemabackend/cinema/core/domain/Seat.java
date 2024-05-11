package org.example.cinemabackend.cinema.core.domain;

public class Seat {
    private Long id;
    private Integer seatRow;
    private Integer seatColumn;
    private SeatZone seatZone;
    private SeatType seatType;

    public Seat(Integer seatRow, Integer seatColumn, SeatZone seatZone, SeatType seatType) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.seatZone = seatZone;
        this.seatType = seatType;
    }

    public Seat(Long id, Integer seatRow, Integer seatColumn, SeatZone seatZone, SeatType seatType) {
        this.id = id;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.seatZone = seatZone;
        this.seatType = seatType;
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

    public SeatType getSeatType() {
        return seatType;
    }

    public Long getId() {
        return id;
    }
}
