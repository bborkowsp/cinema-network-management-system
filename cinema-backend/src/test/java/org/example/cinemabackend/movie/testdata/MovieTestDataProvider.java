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
    private static final String MOVIE_TITLE = "Movie Title";
    private static final String UPDATED_MOVIE_TITLE = "Updated Movie Title";
    private static final String ORIGINAL_MOVIE_TITLE = "Original Movie Title";
    private static final String UPDATED_ORIGINAL_MOVIE_TITLE = "Updated Original Movie Title";
    private static final Integer DURATION = 120;
    private static final Integer UPDATED_DURATION = 130;
    private static final LocalDate RELEASE_DATE = LocalDate.of(2024, 10, 5);
    private static final LocalDate UPDATED_RELEASE_DATE = LocalDate.of(2025, 11, 6);
    private static final String TRAILER_URL = "https://www.youtube.com/embed/ZiGdHLQD300";
    private static final String UPDATED_TRAILER_URL = "https://www.youtube.com/embed/XXXXXXXX";
    private static final String POSTER_FILENAME = "poster.jpg";
    private static final String DESCRIPTION = "Description";
    private static final String UPDATED_DESCRIPTION = "Updated Description";
    private final ProjectionTechnologyTestDataProvider productionDetailsTestDataProvider;
    private final ProjectionTechnologyRepository projectionTechnologyRepository;
    private int moviesCounter = -1;

    public List<Movie> generateMovies() {
        List<Movie> movies = new ArrayList<>();
        final var projectionTechnologies = generateProjectionTechnologies();
        productionDetailsTestDataProvider.saveProjectionTechnologiesToDatabase(projectionTechnologies);

        for (int i = 0; i < NUMBER_OF_MOVIES_TO_GENERATE; i++) {
            final var movie = generateMovie();
            movie.setPoster(POSTER_FILENAME);
            movies.add(movie);
        }
        return movies;
    }

    private Movie generateMovie() {
        moviesCounter++;
        return new Movie(
                MOVIE_TITLE + moviesCounter,
                ORIGINAL_MOVIE_TITLE,
                DURATION,
                RELEASE_DATE,
                generateProductionDetails(),
                DESCRIPTION + moviesCounter,
                generateSubtitleAndSoundOptions(),
                AgeRestriction.PLUS4,
                TRAILER_URL,
                Set.of(Genre.ACTION),
                new HashSet<>(projectionTechnologyRepository.findAll())
        );
    }

    public CreateMovieRequest generateCreateMovieRequest() {
        return new CreateMovieRequest(
                MOVIE_TITLE,
                ORIGINAL_MOVIE_TITLE,
                DURATION,
                RELEASE_DATE,
                DESCRIPTION,
                generateProductionDetailsRequest(),
                generateSubtitleAndSoundOptionsRequest(),
                AgeRestriction.PLUS7,
                TRAILER_URL,
                Set.of(Genre.CRIME),
                productionDetailsTestDataProvider.generateProjectionTechnologiesResponse()
        );
    }

    public UpdateMovieRequest generateUpdateMovieRequest() {
        return new UpdateMovieRequest(
                UPDATED_MOVIE_TITLE,
                UPDATED_ORIGINAL_MOVIE_TITLE,
                UPDATED_DURATION,
                UPDATED_RELEASE_DATE,
                UPDATED_DESCRIPTION,
                generateProductionDetailsRequest(),
                generateSubtitleAndSoundOptionsRequest(),
                AgeRestriction.PLUS12,
                UPDATED_TRAILER_URL,
                Set.of(Genre.ADVENTURE),
                productionDetailsTestDataProvider.generateProjectionTechnologiesResponse()
        );
    }
}