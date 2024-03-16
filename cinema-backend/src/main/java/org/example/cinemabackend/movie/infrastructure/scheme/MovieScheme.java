package org.example.cinemabackend.movie.infrastructure.scheme;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.example.cinemabackend.cinema.infrastructure.config.AbstractEntitySchema;
import org.example.cinemabackend.movie.core.domain.Movie;

@Entity
@Table(name = "movies")
public class MovieScheme extends AbstractEntitySchema<Long> {

    @Column(nullable = false, unique = true)
    private String title;

    public static MovieScheme fromMovie(Movie movie) {
        return null;
    }

    public Movie toMovie() {
        Movie movie = new Movie();
        movie.setId(this.getId());
        return movie;
    }
}
