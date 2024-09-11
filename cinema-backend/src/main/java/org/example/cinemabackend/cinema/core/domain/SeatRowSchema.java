package org.example.cinemabackend.cinema.core.domain;

import jakarta.persistence.*;
import org.example.cinemabackend.cinema.infrastructure.schema.SeatSchema;

import java.util.List;

@Entity
public class SeatRowSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany
    private List<SeatSchema> seats;
}
