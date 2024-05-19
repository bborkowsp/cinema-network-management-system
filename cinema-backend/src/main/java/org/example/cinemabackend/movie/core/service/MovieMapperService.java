package org.example.cinemabackend.movie.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyResponse;
import org.example.cinemabackend.cinema.core.port.primary.ImageMapper;
import org.example.cinemabackend.cinema.core.port.primary.ProjectionTechnologyMapper;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.example.cinemabackend.movie.application.dto.request.CreateMovieRequest;
import org.example.cinemabackend.movie.application.dto.request.UpdateMovieRequest;
import org.example.cinemabackend.movie.application.dto.response.MovieListResponse;
import org.example.cinemabackend.movie.application.dto.response.MovieResponse;
import org.example.cinemabackend.movie.core.domain.Image;
import org.example.cinemabackend.movie.core.domain.Movie;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.example.cinemabackend.movie.core.port.primary.*;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class MovieMapperService implements MovieMapper {

    private final ImageMapper imageMapper;
    private final VideoFileMapper videoFileMapper;
    private final FilmMemberMapper filmMemberMapper;
    private final ProductionDetailsMapper productionDetailsMapper;
    private final ProjectionTechnologyMapper projectionTechnologyMapper;
    private final SubtitleAndSoundOptionsMapper subtitleAndSoundOptionsMapper;
    private final ProjectionTechnologyRepository projectionTechnologyRepository;

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
                .originalTitle(movie.getOriginalTitle())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .productionDetails(productionDetailsMapper.mapProductionDetailsToProductionDetailsResponse(movie.getProductionDetails()))
                .description(movie.getDescription())
                .subtitleAndSoundOptions(subtitleAndSoundOptionsMapper.mapSubtitleAndSoundOptionsToSubtitleAndSoundOptionsResponse(movie.getSubtitleAndSoundOptions()))
                .ageRestriction(movie.getAgeRestriction())
                .poster(imageMapper.mapImageToImageResponse(getMoviePoster(movie)))
                .trailer(videoFileMapper.mapVideoFileToVideoFileResponse(movie.getTrailer()))
                .genres(movie.getGenres())
                .projectionTechnologies(projectionTechnologyMapper.mapProjectionTechnologiesToProjectionTechnologyResponses(movie.getProjectionTechnologies()))
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
                subtitleAndSoundOptionsMapper.mapCreateSubtitleAndSoundOptionsRequestToSubtitleAndSoundOptions(createMovieRequest.subtitleAndSoundOptions()),
                createMovieRequest.ageRestriction(),
                imageMapper.mapCreateImageRequestToImage(createMovieRequest.image()),
                videoFileMapper.mapVideoFileRequestToVideoFile(createMovieRequest.trailer()),
                createMovieRequest.genres(),
                getProjectionTechnologies(createMovieRequest.projectionTechnologies())
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
        movie.setSubtitleAndSoundOptions(subtitleAndSoundOptionsMapper.mapUpdateSubtitleAndSoundOptionsRequestToSubtitleAndSoundOptions(updateMovieRequest.subtitleAndSoundOptions(), movie.getSubtitleAndSoundOptions()));
        movie.setAgeRestriction(updateMovieRequest.ageRestriction());
        movie.setPoster(imageMapper.mapUpdateImageRequestToImage(updateMovieRequest.image(), getMoviePoster(movie)));
        movie.setTrailer(videoFileMapper.mapUpdateVideoFileRequestToVideoFile(updateMovieRequest.trailer(), movie.getTrailer()));
        movie.setGenres(updateMovieRequest.genres());
        movie.setProjectionTechnologies(getProjectionTechnologies(updateMovieRequest.projectionTechnologies()));
    }


    private Set<ProjectionTechnology> getProjectionTechnologies(Set<ProjectionTechnologyResponse> projectionTechnologyResponses) {
        return projectionTechnologyResponses.stream()
                .map(projectionTechnologyResponse -> projectionTechnologyRepository.findByTechnology(projectionTechnologyResponse.technology()).orElseThrow())
                .collect(Collectors.toSet());
    }

    private Image getMoviePoster(Movie movie) {
        return movie.getPoster();
    }
}
