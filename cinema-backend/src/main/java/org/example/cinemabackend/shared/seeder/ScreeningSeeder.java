package org.example.cinemabackend.shared.seeder;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.movie.core.domain.Movie;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(4)
class ScreeningSeeder implements Seeder {
    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;

    @Override
    public void seedDatabase(int objectsToSeed) {
        final var cinemas = cinemaRepository.findAll();
        cinemas.forEach(cinema -> {
            createRepertory(cinema);
            cinemaRepository.save(cinema);
        });
    }

    private void createRepertory(Cinema cinema) {
        List<Screening> repertory = new ArrayList<>();
        for (int i = 0; i < 2; i++)
            repertory.add(createScreening(cinema.getScreeningRooms()));

        cinema.setRepertory(repertory);
    }

    private Screening createScreening(Set<ScreeningRoom> screeningRooms) {
        final var movie = getMovie();
        final var startTime = LocalDateTime.now().plusDays(1);
        final var endTime = startTime.plusHours(2);
        return new Screening(
                movie, startTime, endTime, screeningRooms.stream().findAny().get()
        );
    }

    private Movie getMovie() {
        return movieRepository.findAll().stream().findAny().get();
    }
}
