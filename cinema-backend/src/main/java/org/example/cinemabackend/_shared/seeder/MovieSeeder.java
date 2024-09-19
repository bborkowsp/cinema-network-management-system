package org.example.cinemabackend._shared.seeder;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
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
    private static final String TRAILER_URL = "https://www.youtube.com/embed/ZiGdHLQD300";
    private static final String POSTER_FILENAME = "poster.jpg";
    private final MovieRepository movieRepository;
    private final Faker faker;
    private int increment = 0;

    @Override
    public void seedDatabase(int objectsToSeed) {
        Set<Movie> movies = new HashSet<>();
        while (movies.size() < objectsToSeed) {
            final var movie = createMovie();
            movie.setPoster(POSTER_FILENAME);
            movieRepository.save(movie);
            movies.add(movie);
            increment++;
        }
    }

    private Movie createMovie() {
        final var productionDetails = createProductionDetails();
        final var ageRestriction = createAgeRestriction();
        final var genres = createGenres();
        final var movieVariants = createMovieVariants();
        return new Movie(
                faker.book().title() + increment,
                faker.book().title(),
                faker.number().numberBetween(60, 180),
                faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                productionDetails,
                faker.lorem().sentence(50),
                ageRestriction,
                TRAILER_URL,
                genres,
                movieVariants
        );
    }

    private Set<MovieVariant> createMovieVariants() {
        return Set.of(
                new MovieVariant(ProjectionTechnology._2D, Language.DUBBING),
                new MovieVariant(ProjectionTechnology._3D, Language.SUBTITLES)
        );
    }


    private ProductionDetails createProductionDetails() {
        final var director = createFilmMember();
        final var actors = createFilmMembers();
        return new ProductionDetails(
                faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                director,
                actors,
                createOriginalLanguages(),
                createProductionCountries()
        );
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

    private AgeRestriction createAgeRestriction() {
        return AgeRestriction.values()[faker.number().numberBetween(0, AgeRestriction.values().length)];
    }

    private Set<Genre> createGenres() {
        Set<Genre> genres = new HashSet<>();
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.ACTION);
        return genres;
    }
}
