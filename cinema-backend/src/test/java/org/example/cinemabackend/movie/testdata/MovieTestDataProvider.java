package org.example.cinemabackend.movie.testdata;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.example.cinemabackend.cinema.testdata.ProjectionTechnologyTestDataProvider;
import org.example.cinemabackend.movie.application.dto.request.CreateMovieRequest;
import org.example.cinemabackend.movie.application.dto.request.UpdateMovieRequest;
import org.example.cinemabackend.movie.core.domain.AgeRestriction;
import org.example.cinemabackend.movie.core.domain.Genre;
import org.example.cinemabackend.movie.core.domain.Movie;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.example.cinemabackend._shared.seeder.ImageUtil.createImage;
import static org.example.cinemabackend.cinema.testdata.ImageTestDataProvider.generateCreateImageRequest;
import static org.example.cinemabackend.cinema.testdata.ImageTestDataProvider.generateUpdateImageRequest;
import static org.example.cinemabackend.cinema.testdata.ProjectionTechnologyTestDataProvider.generateProjectionTechnologies;
import static org.example.cinemabackend.movie.testdata.ProductionDetailsTestDataProvider.generateProductionDetails;
import static org.example.cinemabackend.movie.testdata.ProductionDetailsTestDataProvider.generateProductionDetailsRequest;
import static org.example.cinemabackend.movie.testdata.SubtitleAndSoundOptionTestDataProvider.generateSubtitleAndSoundOptions;
import static org.example.cinemabackend.movie.testdata.SubtitleAndSoundOptionTestDataProvider.generateSubtitleAndSoundOptionsRequest;
import static org.example.cinemabackend.movie.testdata.VideoFileTestDataProvider.generateVideoFile;
import static org.example.cinemabackend.movie.testdata.VideoFileTestDataProvider.generateVideoFileRequest;

@Component
@RequiredArgsConstructor
public class MovieTestDataProvider {
    private static final int NUMBER_OF_MOVIES_TO_GENERATE = 3;
    private static int moviesCounter = -1;
    private final ProjectionTechnologyTestDataProvider productionDetailsTestDataProvider;
    private final ProjectionTechnologyRepository projectionTechnologyRepository;

    public List<Movie> generateMovies() {
        List<Movie> movies = new ArrayList<>();
        final var projectionTechnologies = generateProjectionTechnologies();
        productionDetailsTestDataProvider.saveProjectionTechnologiesToDatabase(projectionTechnologies);

        for (int i = 0; i < NUMBER_OF_MOVIES_TO_GENERATE; i++)
            movies.add(generateMovie());

        return movies;
    }


    public CreateMovieRequest generateCreateMovieRequest() {
        return new CreateMovieRequest(
                "Create Title",
                "Create Original Title",
                1.0,
                LocalDate.now(),
                "Create Description",
                generateProductionDetailsRequest(),
                generateSubtitleAndSoundOptionsRequest(),
                AgeRestriction.PLUS12,
                generateCreateImageRequest(),
                generateVideoFileRequest(),
                Set.of(Genre.ADVENTURE),
                productionDetailsTestDataProvider.generateProjectionTechnologiesResponse()
        );
    }

    public UpdateMovieRequest generateUpdateMovieRequest() {
        return new UpdateMovieRequest(
                "Updated Title",
                "Updated Original Title",
                1.0,
                LocalDate.now(),
                "Updated Description",
                generateProductionDetailsRequest(),
                generateSubtitleAndSoundOptionsRequest(),
                AgeRestriction.PLUS12,
                generateUpdateImageRequest(),
                generateVideoFileRequest(),
                Set.of(Genre.ADVENTURE),
                productionDetailsTestDataProvider.generateProjectionTechnologiesResponse()
        );
    }

    private Movie generateMovie() {
        moviesCounter++;
        return new Movie(
                "Movie No. " + moviesCounter,
                "Original Title" + moviesCounter,
                0.0 + moviesCounter,
                LocalDate.now(),
                generateProductionDetails(),
                "Description" + moviesCounter,
                generateSubtitleAndSoundOptions(),
                AgeRestriction.PLUS4,
                createImage(),
                generateVideoFile(),
                Set.of(Genre.ACTION),
                new HashSet<>(projectionTechnologyRepository.findAll())
        );
    }
}