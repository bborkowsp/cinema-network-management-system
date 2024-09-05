package org.example.cinemabackend._shared.seeder;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.example.cinemabackend.movie.core.domain.*;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;


@Component
@RequiredArgsConstructor
@Order(1)
class MovieSeeder implements Seeder {
    private final MovieRepository movieRepository;
    private final ProjectionTechnologyRepository projectionTechnologyRepository;
    private final Faker faker;
    private int increment = 0;

    @Override
    public void seedDatabase(int objectsToSeed) {
        Set<Movie> movies = new HashSet<>();
        while (movies.size() < objectsToSeed) {
            final var movie = createMovie();
            movie.setPoster("poster.jpg");
            movieRepository.save(movie);
            movies.add(movie);
            increment++;
        }
    }

    private Movie createMovie() {
        final var productionDetails = createProductionDetails();
        final var subtitleAndSoundOptions = createSubtitleAndSoundOptions();
        final var ageRestriction = createAgeRestriction();
        final var movieFile = createMovieFile();
        final var genres = createGenres();
        final var projectionTechnologies = getProjectionTechnologies();
        return new Movie(faker.book().title() + increment,
                faker.book().title(),
                faker.number().numberBetween(60, 180),
                faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                productionDetails,
                faker.lorem().sentence(8),
                subtitleAndSoundOptions, ageRestriction,
                movieFile, genres, projectionTechnologies
        );
    }

    private ProductionDetails createProductionDetails() {
        final var director = createFilmMember();
        final var actors = createFilmMembers();
        return new ProductionDetails(
                faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                director, actors, createOriginalLanguages(), createProductionCountries()
        );
    }

    private SubtitleAndSoundOptions createSubtitleAndSoundOptions() {
        return new SubtitleAndSoundOptions(
                faker.bool().bool(),
                faker.bool().bool(),
                faker.bool().bool(),
                faker.bool().bool()
        );
    }

    private AgeRestriction createAgeRestriction() {
        return AgeRestriction.values()[faker.number().numberBetween(0, AgeRestriction.values().length)];
    }

    private VideoFile createMovieFile() {
        return new VideoFile("https://www.youtube.com/embed/ZiGdHLQD300");
    }

    private Set<Genre> createGenres() {
        Set<Genre> genres = new HashSet<>();
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.ACTION);
        return genres;
    }

    private Set<ProjectionTechnology> getProjectionTechnologies() {
        return Set.of(projectionTechnologyRepository.findAll().getFirst(), projectionTechnologyRepository.findAll().getLast());
    }

    private FilmMember createFilmMember() {
        return new FilmMember(faker.name().firstName(), faker.name().lastName());
    }

    private Set<FilmMember> createFilmMembers() {
        Set<FilmMember> actors = new HashSet<>();
        actors.add(createFilmMember());
        return actors;
    }

    private Set<String> createOriginalLanguages() {
        Set<String> originalLanguages = new HashSet<>();
        originalLanguages.add(faker.nation().language());
        return originalLanguages;
    }

    private Set<String> createProductionCountries() {
        Set<String> productionCountries = new HashSet<>();
        productionCountries.add(faker.address().country());
        return productionCountries;
    }
}