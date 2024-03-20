package org.example.cinemabackend.cinema.core.domain;

public class Seat {
    private String seatNumber;
    private String seatLetter;
    private SeatZone seatZone;
    private SeatType seatType;

    public Seat(String seatNumber, String seatLetter, SeatZone seatZone, SeatType seatType) {
        this.seatNumber = seatNumber;
        this.seatLetter = seatLetter;
        this.seatZone = seatZone;
        this.seatType = seatType;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getSeatLetter() {
        return seatLetter;
    }

    public SeatZone getSeatZone() {
        return seatZone;
    }

    public SeatType getSeatType() {
        return seatType;
    }
}
