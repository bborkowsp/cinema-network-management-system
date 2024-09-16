package org.example.cinemabackend.movie.core.port.primary;

import org.example.cinemabackend.movie.application.dto.response.MovieVariantResponse;
import org.example.cinemabackend.movie.core.domain.MovieVariant;

import java.util.Set;

public interface MovieVariantMapper {
    Set<MovieVariantResponse> mapMovieVariantsToMovieVariantResponses(Set<MovieVariant> movieVariants);

    MovieVariantResponse mapMovieVariantToMovieVariantResponse(MovieVariant movieVariant);

    Set<MovieVariant> mapMovieVariantResponsesToMovieVariants(Set<MovieVariantResponse> movieVariantResponses);

    MovieVariant mapMovieVariantResponseToMovieVariant(MovieVariantResponse movieVariantResponse);
}
