package org.example.cinemabackend.ticketing.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.infrastructure.schema.CinemaSchema;
import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningSchema;
import org.example.cinemabackend.cinema.infrastructure.schema.SeatSchema;
import org.example.cinemabackend.user.infrastructure.schema.UserSchema;

import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TicketSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticketNumber;
    private String qrCode;

    @OneToOne
    private UserSchema customer;

    @OneToMany
    private Set<SeatSchema> bookedSeats;

    @OneToOne
    private ScreeningSchema Screening;

    @OneToOne
    private CinemaSchema cinema;
}
