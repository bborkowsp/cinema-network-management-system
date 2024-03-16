package org.example.cinemabackend.cinema.infrastructure.scheme;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.infrastructure.config.AbstractEntitySchema;

@Entity
@Table(name = "cinemas")
public class CinemaScheme extends AbstractEntitySchema<Long> {

    @Column(nullable = false, unique = true)
    private String name;

    public static CinemaScheme fromCinema(Cinema cinema) {
        return null;
    }

    public Cinema toCinema() {
        Cinema cinema = new Cinema();
        cinema.setId(this.getId());
        return cinema;
    }
}
