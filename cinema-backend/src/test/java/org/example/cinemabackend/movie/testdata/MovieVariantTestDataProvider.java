package org.example.cinemabackend.movie.testdata;

import org.example.cinemabackend.movie.application.dto.response.MovieVariantResponse;
import org.example.cinemabackend.movie.core.domain.Language;
import org.example.cinemabackend.movie.core.domain.MovieVariant;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnologyEnum;

import java.util.Set;

public class MovieVariantTestDataProvider {
    public static Set<MovieVariant> generateMovieVariants() {
        return Set.of(
                new MovieVariant(ProjectionTechnologyEnum._2D, Language.DUBBING)
        );
    }

    public static Set<MovieVariantResponse> generateMovieVariantResponses() {
        return Set.of(
                new MovieVariantResponse(ProjectionTechnologyEnum._2D, Language.DUBBING)
        );
    }
}
