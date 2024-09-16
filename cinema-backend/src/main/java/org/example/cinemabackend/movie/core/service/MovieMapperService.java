package org.example.cinemabackend.movie.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.movie.application.dto.request.CreateMovieRequest;
import org.example.cinemabackend.movie.application.dto.request.UpdateMovieRequest;
import org.example.cinemabackend.movie.application.dto.response.MovieListResponse;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;
import org.example.cinemabackend.movie.core.domain.Movie;
import org.example.cinemabackend.movie.core.port.primary.FilmMemberMapper;
import org.example.cinemabackend.movie.core.port.primary.MovieMapper;
import org.example.cinemabackend.movie.core.port.primary.MovieVariantMapper;
import org.example.cinemabackend.movie.core.port.primary.ProductionDetailsMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MovieMapperService implements MovieMapper {
    private final FilmMemberMapper filmMemberMapper;
    private final ProductionDetailsMapper productionDetailsMapper;
    private final MovieVariantMapper movieVariantMapper;

    @Override
    public MovieListResponse mapMovieToMovieListResponse(Movie movie) {
        return MovieListResponse.builder()
                .title(movie.getTitle())
                .originalTitle(movie.getOriginalTitle())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .poster(movie.getPoster())
                .director(filmMemberMapper.mapFilmMemberToFilmMemberResponse(movie.getProductionDetails().getDirector()))
                .build();
    }

    @Override
    public MovieResponse mapMovieToMovieResponse(Movie movie) {
        return MovieResponse.builder()
                .title(movie.getTitle())
                .originalTitle(movie.getOriginalTitle())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .productionDetails(productionDetailsMapper.mapProductionDetailsToProductionDetailsResponse(movie.getProductionDetails()))
                .description(movie.getDescription())
                .poster(movie.getPoster())
                .ageRestriction(movie.getAgeRestriction())
                .trailer(movie.getTrailer())
                .genres(movie.getGenres())

                .build();
    }

    @Override
    public Movie mapCreateMovieRequestToMovie(CreateMovieRequest createMovieRequest) {
        return new Movie(
                createMovieRequest.title(),
                createMovieRequest.originalTitle(),
                createMovieRequest.duration(),
                createMovieRequest.releaseDate(),
                productionDetailsMapper.mapCreateProductionDetailsRequestToProductionDetails(createMovieRequest.productionDetails()),
                createMovieRequest.description(),
                createMovieRequest.ageRestriction(),
                createMovieRequest.trailer(),
                createMovieRequest.genres(),
                movieVariantMapper.mapMovieVariantResponsesToMovieVariants(createMovieRequest.movieVariants())
        );
    }

    @Override
    public void updateMovieFromUpdateMovieRequest(UpdateMovieRequest updateMovieRequest, Movie movie) {
        movie.setTitle(updateMovieRequest.title());
        movie.setOriginalTitle(updateMovieRequest.originalTitle());
        movie.setDuration(updateMovieRequest.duration());
        movie.setReleaseDate(updateMovieRequest.releaseDate());
        movie.setProductionDetails(productionDetailsMapper.mapUpdateProductionDetailsRequestToProductionDetails(updateMovieRequest.productionDetails(), movie.getProductionDetails()));
        movie.setDescription(updateMovieRequest.description());
        movie.setAgeRestriction(updateMovieRequest.ageRestriction());
        movie.setTrailer(movie.getTrailer());
        movie.setGenres(updateMovieRequest.genres());
        movie.setMovieVariants(movieVariantMapper.mapMovieVariantResponsesToMovieVariants(updateMovieRequest.movieVariants()));
    }
}
