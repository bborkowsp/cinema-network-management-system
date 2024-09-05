package org.example.cinemabackend.movie.testdata;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.testdata.ProjectionTechnologyTestDataProvider;
import org.example.cinemabackend.movie.application.dto.request.CreateMovieRequest;
import org.example.cinemabackend.movie.application.dto.request.UpdateMovieRequest;
import org.example.cinemabackend.movie.core.domain.AgeRestriction;
import org.example.cinemabackend.movie.core.domain.Genre;
import org.example.cinemabackend.movie.core.domain.Movie;
import org.example.cinemabackend.projectiontechnology.core.port.secondary.ProjectionTechnologyRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.example.cinemabackend.cinema.testdata.ProjectionTechnologyTestDataProvider.generateProjectionTechnologies;
import static org.example.cinemabackend.movie.testdata.ProductionDetailsTestDataProvider.generateProductionDetails;
import static org.example.cinemabackend.movie.testdata.ProductionDetailsTestDataProvider.generateProductionDetailsRequest;
import static org.example.cinemabackend.movie.testdata.SubtitleAndSoundOptionTestDataProvider.generateSubtitleAndSoundOptions;
import static org.example.cinemabackend.movie.testdata.SubtitleAndSoundOptionTestDataProvider.generateSubtitleAndSoundOptionsRequest;

@Component
@RequiredArgsConstructor
public class MovieTestDataProvider {
    private static final int NUMBER_OF_MOVIES_TO_GENERATE = 3;
    private static final String TRAILER_URL = "https://www.youtube.com/embed/ZiGdHLQD300";
    private static int moviesCounter = -1;
    private final ProjectionTechnologyTestDataProvider productionDetailsTestDataProvider;
    private final ProjectionTechnologyRepository projectionTechnologyRepository;

    public List<Movie> generateMovies() {
        List<Movie> movies = new ArrayList<>();
        final var projectionTechnologies = generateProjectionTechnologies();
        productionDetailsTestDataProvider.saveProjectionTechnologiesToDatabase(projectionTechnologies);

        for (int i = 0; i < NUMBER_OF_MOVIES_TO_GENERATE; i++) {
            final var movie = generateMovie();
            movie.setPoster("poster.jpg");
            movies.add(generateMovie());
        }
        return movies;
    }

    private Movie generateMovie() {
        moviesCounter++;
        return new Movie(
                "Movie No. " + moviesCounter,
                "Original Title" + moviesCounter,
                moviesCounter,
                LocalDate.now(),
                generateProductionDetails(),
                "Description" + moviesCounter,
                generateSubtitleAndSoundOptions(),
                AgeRestriction.PLUS4,
                TRAILER_URL,
                Set.of(Genre.ACTION),
                new HashSet<>(projectionTechnologyRepository.findAll())
        );
    }

    public CreateMovieRequest generateCreateMovieRequest() {
        return new CreateMovieRequest(
                "Create Title",
                "Create Original Title",
                10,
                LocalDate.now(),
                "Create Description",
                generateProductionDetailsRequest(),
                generateSubtitleAndSoundOptionsRequest(),
                AgeRestriction.PLUS12,
                TRAILER_URL,
                Set.of(Genre.ADVENTURE),
                productionDetailsTestDataProvider.generateProjectionTechnologiesResponse()
        );
    }

    public UpdateMovieRequest generateUpdateMovieRequest() {
        return new UpdateMovieRequest(
                "Updated Title",
                "Updated Original Title",
                10,
                LocalDate.now(),
                "Updated Description",
                generateProductionDetailsRequest(),
                generateSubtitleAndSoundOptionsRequest(),
                AgeRestriction.PLUS12,
                TRAILER_URL,
                Set.of(Genre.ADVENTURE),
                productionDetailsTestDataProvider.generateProjectionTechnologiesResponse()
        );
    }
}