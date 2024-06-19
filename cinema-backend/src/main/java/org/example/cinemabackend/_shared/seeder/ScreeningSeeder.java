package org.example.cinemabackend._shared.seeder;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.Screening;
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
        cinemaRepository.findAll().forEach(this::createAndSaveRepertory);
    }

    private void createAndSaveRepertory(Cinema cinema) {
        cinema.getScreeningRooms().forEach(screeningRoom -> {
            final var repertory = createRepertory();
            screeningRoom.setRepertory(repertory);
            screeningRoomRepository.save(screeningRoom);
        });
    }

    private List<Screening> createRepertory() {
        List<Screening> repertory = new ArrayList<>();
        for (int i = 0; i < SCREENINGS_PER_CINEMA; i++) {
            repertory.add(createScreening());
        }
        return repertory;
    }

    private Screening createScreening() {
        final var movie = getMovie();
        final var startTime = LocalDateTime.now().plusDays(1);
        final var endTime = startTime.plusHours(2);
        return new Screening(movie, startTime, endTime);
    }

    private Movie getMovie() {
        return movieRepository.findAll().stream().findAny().orElseThrow();
    }
}
