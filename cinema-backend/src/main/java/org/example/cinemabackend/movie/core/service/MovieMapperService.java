package org.example.cinemabackend.movie.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.port.primary.ImageMapper;
import org.example.cinemabackend.movie.application.dto.request.CreateMovieRequest;
import org.example.cinemabackend.movie.application.dto.response.MovieListResponse;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;
import org.example.cinemabackend.movie.core.domain.Image;
import org.example.cinemabackend.movie.core.domain.Movie;
import org.example.cinemabackend.movie.core.port.primary.FilmMemberMapper;
import org.example.cinemabackend.movie.core.port.primary.MovieMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class MovieMapperService implements MovieMapper {

    private final ImageMapper imageMapper;
    private final FilmMemberMapper filmMemberMapper;

    @Override
    public MovieListResponse mapMovieToMovieListResponse(Movie movie) {
        return MovieListResponse.builder()
                .title(movie.getTitle())
                .originalTitle(movie.getOriginalTitle())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .image(imageMapper.mapImageToImageResponse(getMoviePoster(movie)))
                .director(filmMemberMapper.mapFilmMemberToFilmMemberResponse(movie.getProductionDetails().getDirector()))
                .build();
    }

    @Override
    public MovieResponse mapMovieToMovieResponse(Movie movie) {
        return MovieResponse.builder()
                .title(movie.getTitle())
                .image(imageMapper.mapImageToImageResponse(getMoviePoster(movie)))
                .build();
    }

    @Override
    public Movie mapCreateMovieRequestToMovie(CreateMovieRequest createMovieRequest) {
        return null;
    }

    private Image getMoviePoster(Movie movie) {
        return movie.getImages().stream()
                .findFirst()
                .orElse(null);
    }
}
