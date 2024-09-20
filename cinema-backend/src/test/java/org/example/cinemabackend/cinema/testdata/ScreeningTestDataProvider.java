package org.example.cinemabackend.cinema.testdata;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateScreeningRequest;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.movie.core.domain.Language;
import org.example.cinemabackend.movie.core.domain.MovieVariant;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.example.cinemabackend.movie.testdata.MovieVariantTestDataProvider;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ScreeningTestDataProvider {
    public static final LocalDateTime startTime = LocalDateTime.of(2022, 1, 1, 10, 0);
    private static final int NUMBER_OF_SCREENINGS_IN_REPERTORY = 3;
    private static final LocalDateTime endTime = startTime.plusHours(2);
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final MovieVariantTestDataProvider movieVariantTestDataProvider;

    public CreateScreeningRequest generateCreateScreeningRequest() {
        final var movie = movieRepository.findAll().getFirst();
        final var cinema = cinemaRepository.findAll().getFirst();
        final var screeningRoom = cinema.getScreeningRooms().stream().findFirst().get();
        return new CreateScreeningRequest(
                movie.getTitle(),
                startTime,
                endTime,
                screeningRoom.getName(),
                movieVariantTestDataProvider.generateMovieVariantResponse()
        );
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
