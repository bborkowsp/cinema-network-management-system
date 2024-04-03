package org.example.cinemabackend.movie.core.port.secondary;

public interface MovieRepository {

    boolean findByProjectionTechnology(String technology);
}
