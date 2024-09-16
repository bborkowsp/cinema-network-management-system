package org.example.cinemabackend.movie.core.service;

import jakarta.validation.Valid;
import org.example.cinemabackend.movie.application.dto.response.MovieVariantResponse;
import org.example.cinemabackend.movie.core.domain.MovieVariant;
import org.example.cinemabackend.movie.core.port.primary.MovieVariantMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieVariantMapperService implements MovieVariantMapper {

    @Override
    public Set<MovieVariant> mapMovieVariantResponsesToMovieVariants(Set<MovieVariantResponse> movieVariantResponses) {
        return movieVariantResponses.stream().map(this::mapMovieVariantResponseToMovieVariant).collect(Collectors.toSet());
    }

    @Override
    public MovieVariant mapMovieVariantResponseToMovieVariant(@Valid MovieVariantResponse movieVariantResponse) {
        return new MovieVariant(
                movieVariantResponse.projectionTechnologyEnum(),
                movieVariantResponse.language()
        );
    }
}
