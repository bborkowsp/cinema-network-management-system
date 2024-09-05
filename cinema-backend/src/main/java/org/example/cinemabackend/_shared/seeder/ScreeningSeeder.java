package org.example.cinemabackend._shared.seeder;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRoomRepository;
import org.example.cinemabackend.movie.core.domain.Movie;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(4)
class ScreeningSeeder implements Seeder {
    private static final int SCREENINGS_PER_CINEMA = 2;
    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;
    private final ScreeningRoomRepository screeningRoomRepository;

    @Override
    public void seedDatabase(int objectsToSeed) {
        List<Movie> movies = new ArrayList<>(movieRepository.findAll());
        cinemaRepository.findAll().forEach(cinema -> createAndSaveRepertory(cinema, movies));
    }

    private void createAndSaveRepertory(Cinema cinema, List<Movie> movies) {
        List<ScreeningRoom> updatedScreeningRooms = new ArrayList<>();

        cinema.getScreeningRooms().forEach(screeningRoom -> {
            final var repertory = createRepertory(movies);
            screeningRoom.setRepertory(repertory);
            updatedScreeningRooms.add(screeningRoom);
        });

        screeningRoomRepository.saveAll(updatedScreeningRooms);
    }

    private List<Screening> createRepertory(List<Movie> movies) {
        List<Screening> repertory = new ArrayList<>(SCREENINGS_PER_CINEMA);
        for (int i = 0; i < SCREENINGS_PER_CINEMA; i++) {
            repertory.add(createScreening(i, movies));
        }
        return repertory;
    }

    private Screening createScreening(int i, List<Movie> movies) {
        final var movie = getMovie(i, movies);
        final var startTime = LocalDateTime.now().plusDays(1);
        final var endTime = startTime.plusHours(2);
        return new Screening(movie, startTime, endTime);
    }

    private Movie getMovie(int i, List<Movie> movies) {
        // Assuming the list has at least two movies
        return i % 2 == 0 ? movies.getLast() : movies.getFirst();
    }
}
