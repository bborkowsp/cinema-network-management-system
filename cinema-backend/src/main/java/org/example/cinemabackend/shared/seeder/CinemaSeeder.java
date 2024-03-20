package org.example.cinemabackend.shared.seeder;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cinemabackend.cinema.core.domain.*;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.movie.core.domain.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
class CinemaSeeder implements Seeder {
    private final CinemaRepository cinemaRepository;
    private final Faker faker;

    @Override
    public void seedDatabase(int objectsToSeed) {
        Set<Cinema> cinemas = new HashSet<>();
        while (cinemas.size() < objectsToSeed) {
            final var cinema = createCinema();
            cinemaRepository.save(cinema);
            cinemas.add(cinema);
        }
    }

    private Cinema createCinema() {
        final var address = createAddress();
        final var repertory = createRepertory();
        final var image = createImage();
        final var screeningRooms = createScreeningRooms();
        final var contactDetails = createContactDetails();
        return new Cinema(faker.company().name(), faker.lorem().fixedString(100), address, image, repertory, screeningRooms, contactDetails);
    }

    private Set<ContactDetails> createContactDetails() {
        Set<ContactDetails> contactDetails = new HashSet<>();
        final var contactType = createContactType();
        contactDetails.add(new ContactDetails(faker.lorem().fixedString(10), contactType));
        return contactDetails;
    }

    private ContactType createContactType() {
        System.out.println("Creating contact type");
        System.out.println(faker.phoneNumber().cellPhone());

        return new ContactType("+48 123 123 123", faker.internet().emailAddress());
    }

    private Set<ScreeningRoom> createScreeningRooms() {
        Set<ScreeningRoom> screeningRooms = new HashSet<>();
        screeningRooms.add(createScreeningRoom());
        return screeningRooms;
    }

    private ScreeningRoom createScreeningRoom() {
        final var seats = createSeats();
        final var projectionTechnologies = createProjectionTechnologies();
        return new ScreeningRoom(faker.lorem().fixedString(10), seats, projectionTechnologies);
    }

    private Image createImage() {
        return new Image(faker.lorem().fixedString(10), faker.lorem().fixedString(10), new byte[100]);
    }

    private Set<Screening> createRepertory() {
        Set<Screening> repertory = new HashSet<>();
        repertory.add(createScreening());
        return repertory;
    }

    private Screening createScreening() {
        final var movie = createNewMovie();
        final var screeningTimes = createScreeningTimes();
        return new Screening(movie, screeningTimes);
    }

    private List<ScreeningTime> createScreeningTimes() {
        final var ScreeningTime = createScreeningTime();
        return List.of(ScreeningTime);
    }

    private ScreeningTime createScreeningTime() {
        return new ScreeningTime(createScreeningRoom(), LocalDateTime.now().plusHours(2));
    }

    private Set<Seat> createSeats() {
        Set<Seat> seats = new HashSet<>();
        seats.add(createSeat());
        return seats;
    }

    private Seat createSeat() {
        return new Seat(String.valueOf(faker.number().numberBetween(1, 19)), faker.lorem().fixedString(1),
                SeatZone.PROMO, SeatType.OCCUPIED);
    }

    private Movie createNewMovie() {
        final var productionDetails = createProductionDetails();
        final var description = createDescription();
        final var subtitleAndSoundOptions = createSubtitleAndSoundOptions();
        final var ageRestriction = createAgeRestriction();
        final var movieFile = createMovieFile();
        return new Movie(faker.book().title(),
                faker.book().title(),
                faker.number().randomDouble(2, 60, 180),
                faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                productionDetails, description, subtitleAndSoundOptions, ageRestriction,
                movieFile, createImages(), createTrailers(), createGenres(), createProjectionTechnologies());


    }

    private Set<ProjectionTechnology> createProjectionTechnologies() {
        Set<ProjectionTechnology> projectionTechnologies = new HashSet<>();
        projectionTechnologies.add(createProjectionTechnology());
        return projectionTechnologies;
    }

    private ProjectionTechnology createProjectionTechnology() {
        return new ProjectionTechnology(faker.lorem().fixedString(10), faker.lorem().fixedString(100));
    }

    private Set<Genre> createGenres() {
        Set<Genre> genres = new HashSet<>();
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.ACTION);
        return genres;
    }

    private Set<VideoFile> createTrailers() {
        Set<VideoFile> trailers = new HashSet<>();
        trailers.add(createMovieFile());
        return trailers;
    }

    private Set<Image> createImages() {
        Set<Image> images = new HashSet<>();
        images.add(createImage());
        return images;
    }

    private VideoFile createMovieFile() {
        return new VideoFile(faker.lorem().fixedString(10), faker.lorem().fixedString(10), new byte[100]);
    }

    private AgeRestriction createAgeRestriction() {
        return AgeRestriction.values()[faker.number().numberBetween(0, AgeRestriction.values().length)];
    }

    private SubtitleAndSoundOptions createSubtitleAndSoundOptions() {
        return new SubtitleAndSoundOptions(faker.bool().bool(), faker.bool().bool(), faker.bool().bool(), faker.bool().bool());
    }

    private Description createDescription() {
        return new Description(faker.lorem().fixedString(100), faker.lorem().fixedString(100));
    }

    private ProductionDetails createProductionDetails() {
        final var director = createFilmMember();
        final var actors = createFilmMembers();
        return new ProductionDetails(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), director, actors, createOriginalLanguages(), createProductionCountries());
    }

    private Set<FilmMember> createFilmMembers() {
        Set<FilmMember> actors = new HashSet<>();
        actors.add(createFilmMember());
        return actors;
    }

    private FilmMember createFilmMember() {
        return new FilmMember(faker.name().firstName(), faker.name().lastName());
    }

    private Set<String> createProductionCountries() {
        Set<String> productionCountries = new HashSet<>();
        productionCountries.add(faker.address().country());
        return productionCountries;
    }

    private Set<String> createOriginalLanguages() {
        Set<String> originalLanguages = new HashSet<>();
        originalLanguages.add(faker.nation().language());
        return originalLanguages;
    }

    private Address createAddress() {
        return new Address(faker.address().streetAddress(), faker.address().buildingNumber(), faker.address().city(), faker.address().zipCode(), faker.address().country());
    }

    @Slf4j
    private static class Logger {
        public static void info(String s) {
        }
    }
}
