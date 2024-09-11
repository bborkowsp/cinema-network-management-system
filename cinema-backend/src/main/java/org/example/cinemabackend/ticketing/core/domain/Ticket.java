package org.example.cinemabackend.ticketing.core.domain;

import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.user.core.domain.User;

import java.util.Set;

public class Ticket {
    private Long id;
    private String ticketNumber;
    private QrCode qrCode;
    private User customer;
    private Set<Seat> bookedSeats;
    private Screening screening;
    private Cinema cinema;

    public Ticket(String ticketNumber, org.example.cinemabackend.ticketing.core.domain.QrCode qrCode, User customer, Set<Seat> bookedSeats, Screening screening, Cinema cinema) {
        this.ticketNumber = ticketNumber;
        this.qrCode = qrCode;
        this.customer = customer;
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

    public User getCustomer() {
        return customer;
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
