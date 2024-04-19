package org.example.cinemabackend.reservation.core.domain;

import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.domain.Seat;

import java.util.Set;

public class Ticket extends AbstractEntity<Long> {
    private String ticketNumber;
    private QrCode qrCode;
    //    private Client client;
    private Set<Seat> bookedSeats;
    private Screening screening;
    private Cinema cinema;

    public Ticket(String ticketNumber, QrCode qrCode, Set<Seat> bookedSeats, Screening screening, Cinema cinema) {
        this.ticketNumber = ticketNumber;
        this.qrCode = qrCode;
//        this.client = client;
        this.bookedSeats = bookedSeats;
        this.screening = screening;
        this.cinema = cinema;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public QrCode getQrCode() {
        return qrCode;
    }

//    public Client getClient() {
//        return client;
//    }

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
