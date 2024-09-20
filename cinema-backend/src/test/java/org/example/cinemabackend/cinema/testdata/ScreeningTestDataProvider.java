package org.example.cinemabackend.cinema.testdata;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.movie.core.domain.Language;
import org.example.cinemabackend.movie.core.domain.MovieVariant;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScreeningTestDataProvider {
    private static final int NUMBER_OF_SCREENINGS_IN_REPERTORY = 3;
    private static final LocalDateTime startTime = LocalDateTime.of(2022, 1, 1, 10, 0);
    private static final LocalDateTime endTime = startTime.plusHours(2);
    private final MovieRepository movieRepository;

    public List<Screening> generateRepertory() {
        List<Screening> screenings = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_SCREENINGS_IN_REPERTORY; i++) {
            final var cinema = generateScreening();
            screenings.add(cinema);
        }
        return screenings;
    }

    private Screening generateScreening() {
        return new Screening(
                movieRepository.findAll().getFirst(),
                startTime,
                endTime,
                new MovieVariant(ProjectionTechnology._2D, Language.SUBTITLES)
        );
    }
}
