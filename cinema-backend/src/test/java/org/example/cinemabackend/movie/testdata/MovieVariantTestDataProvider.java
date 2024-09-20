package org.example.cinemabackend.movie.testdata;

import org.example.cinemabackend.movie.application.dto.response.MovieVariantResponse;
import org.example.cinemabackend.movie.core.domain.Language;
import org.example.cinemabackend.movie.core.domain.MovieVariant;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MovieVariantTestDataProvider {
    public static Set<MovieVariant> generateMovieVariants() {
        return Set.of(
                new MovieVariant(ProjectionTechnology._2D, Language.DUBBING)
        );
    }

    public static Set<MovieVariantResponse> generateMovieVariantResponses() {
        return Set.of(
                new MovieVariantResponse(ProjectionTechnology._2D, Language.DUBBING)
        );
    }

    public MovieVariantResponse generateMovieVariantResponse() {
        return new MovieVariantResponse(ProjectionTechnology._2D, Language.DUBBING);
    }
}
