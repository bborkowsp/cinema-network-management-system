package org.example.cinemabackend._shared.seeder;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRoomRepository;
import org.example.cinemabackend.movie.core.domain.Language;
import org.example.cinemabackend.movie.core.domain.Movie;
import org.example.cinemabackend.movie.core.domain.MovieVariant;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.example.cinemabackend.movie.core.port.secondary.MovieVariantRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(4)
class ScreeningSeeder implements Seeder {
    private static final int SCREENINGS_PER_CINEMA = 6;
    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;
    private final ScreeningRoomRepository screeningRoomRepository;
    private final MovieVariantRepository movieVariantRepository;

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
        final var startTime = LocalDateTime.now().plusHours(i + 1);
        final var endTime = startTime.plusHours(1);
        final var movieVariant = createMovieVariant();
        return new Screening(movie, startTime, endTime, movieVariant);
    }

    private MovieVariant createMovieVariant() {
        return new MovieVariant(
                ProjectionTechnology._2D, Language.DUBBING
        );
    }

    private Movie getMovie(int i, List<Movie> movies) {
        // Assuming the list has at least two movies
        return i % 2 == 0 ? movies.getLast() : movies.getFirst();
    }
}
