package org.example.cinemabackend.ticketing.core.domain;

import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.user.core.domain.User;

import java.util.Set;

public class Ticket {
    private String ticketNumber;
    private org.example.cinemabackend.ticketing.core.domain.QrCode qrCode;
    private User client;
    private Set<Seat> bookedSeats;
    private Screening screening;
    private Cinema cinema;

    public Ticket(String ticketNumber, org.example.cinemabackend.ticketing.core.domain.QrCode qrCode, User client, Set<Seat> bookedSeats, Screening screening, Cinema cinema) {
        this.ticketNumber = ticketNumber;
        this.qrCode = qrCode;
        this.client = client;
        this.bookedSeats = bookedSeats;
        this.screening = screening;
        this.cinema = cinema;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public org.example.cinemabackend.ticketing.core.domain.QrCode getQrCode() {
        return qrCode;
    }

    public User getClient() {
        return client;
    }

    public Set<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public Screening getScreening() {
        return screening;
    }

    public Cinema getCinema() {
        return cinema;
    }
}
