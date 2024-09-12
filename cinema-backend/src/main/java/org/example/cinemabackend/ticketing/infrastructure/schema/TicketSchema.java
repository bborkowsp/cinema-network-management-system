package org.example.cinemabackend.ticketing.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.infrastructure.schema.CinemaSchema;
import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningRoomSchema;
import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningSchema;
import org.example.cinemabackend.cinema.infrastructure.schema.SeatSchema;
import org.example.cinemabackend.ticketing.core.domain.Ticket;
import org.example.cinemabackend.user.infrastructure.schema.UserSchema;

import java.util.List;
import java.util.stream.Collectors;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TicketSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;
    @Column
    private String orderId;
    @Column
    private String firstName;
    @Column
    private String lastName;

    @Lob
    @Column
    private byte[] qrCode;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<SeatSchema> bookedSeats;

    @OneToOne
    private ScreeningSchema screening;

    @OneToOne
    private ScreeningRoomSchema screeningRoom;

    @OneToOne
    private CinemaSchema cinema;

    @OneToOne
    private UserSchema user;

    public static TicketSchema fromTicket(Ticket ticket) {
        return new TicketSchema(
                ticket.getId(),
                ticket.getEmail(),
                ticket.getOrderId(),
                ticket.getFirstName(),
                ticket.getLastName(),
                ticket.getQrCode(),
                ticket.getBookedSeats().stream().map(SeatSchema::fromSeat).collect(Collectors.toList()),
                ScreeningSchema.fromScreening(ticket.getScreening()),
                ScreeningRoomSchema.fromScreeningRoom(ticket.getScreeningRoom()),
                CinemaSchema.fromCinema(ticket.getCinema()),
                ticket.getUser() == null ? null : UserSchema.fromUser(ticket.getUser())
        );
    }

    public Ticket toTicket() {
        return new Ticket(
                id,
                email,
                orderId,
                firstName,
                lastName,
                qrCode,
                bookedSeats.stream().map(SeatSchema::toSeat).collect(Collectors.toList()),
                screening.toScreening(),
                screeningRoom.toScreeningRoom(),
                cinema.toCinema(),
                user == null ? null : user.toUser()
        );
    }
}
