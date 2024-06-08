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
    private static final int SCREENINGS_PER_CINEMA = 2;
    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;

    @Override
    public void seedDatabase(int objectsToSeed) {
        cinemaRepository.findAll().forEach(this::createAndSaveRepertory);
    }

    private void createAndSaveRepertory(Cinema cinema) {
        cinema.setRepertory(createRepertory(cinema));
        cinemaRepository.save(cinema);
    }

    private List<Screening> createRepertory(Cinema cinema) {
        List<Screening> repertory = new ArrayList<>();
        for (int i = 0; i < SCREENINGS_PER_CINEMA; i++) {
            repertory.add(createScreening(cinema));
        }
        return repertory;
    }

    private Screening createScreening(Cinema cinema) {
        final var screeningRoom = getRandomScreeningRoom(cinema.getScreeningRooms());
        final var movie = getMovie();
        final var startTime = LocalDateTime.now().plusDays(1);
        final var endTime = startTime.plusHours(2);
        return new Screening(movie, startTime, endTime, screeningRoom);
    }

    private ScreeningRoom getRandomScreeningRoom(Set<ScreeningRoom> screeningRooms) {
        return screeningRooms.stream().findAny().orElseThrow();
    }

    private Movie getMovie() {
        return movieRepository.findAll().stream().findAny().orElseThrow();
    }
}
