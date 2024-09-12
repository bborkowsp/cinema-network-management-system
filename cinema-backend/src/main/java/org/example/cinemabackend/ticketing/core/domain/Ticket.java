package org.example.cinemabackend.ticketing.core.domain;

import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.user.core.domain.User;

import java.util.List;

public class Ticket {
    private Long id;
    private String orderId;
    private String email;
    private String firstName;
    private String lastName;
    private byte[] qrCode;
    private List<Seat> bookedSeats;
    private Screening screening;
    private ScreeningRoom screeningRoom;
    private Cinema cinema;
    private User user;

    public Ticket(String email, String orderId, String firstName, String lastName, byte[] qrCode, List<Seat> bookedSeats, Screening screening, ScreeningRoom screeningRoom, Cinema cinema) {
        this.email = email;
        this.orderId = orderId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.qrCode = qrCode;
        this.bookedSeats = bookedSeats;
        this.screening = screening;
        this.screeningRoom = screeningRoom;
        this.cinema = cinema;
    }

    public Ticket(Long id, String email, String orderId, String firstName, String lastName, byte[] qrCode, List<Seat> bookedSeats, Screening screening, ScreeningRoom screeningRoom, Cinema cinema, User user) {
        this.id = id;
        this.email = email;
        this.orderId = orderId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.qrCode = qrCode;
        this.bookedSeats = bookedSeats;
        this.screening = screening;
        this.screeningRoom = screeningRoom;
        this.cinema = cinema;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<Seat> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }

    public void setScreeningRoom(ScreeningRoom screeningRoom) {
        this.screeningRoom = screeningRoom;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
