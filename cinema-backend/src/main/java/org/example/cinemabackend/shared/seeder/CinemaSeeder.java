package org.example.cinemabackend.shared.seeder;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.cinemabackend.cinema.core.domain.*;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.movie.core.domain.*;
import org.example.cinemabackend.user.core.domain.CinemaManager;
import org.example.cinemabackend.user.core.domain.Gender;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
class CinemaSeeder implements Seeder {
    private static final int NUMBER_OF_SEATS = 255;
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
        final var cinemaManager = createCinemaManager();
        return new Cinema(faker.company().name(), faker.lorem().fixedString(100),
                address, image, repertory, screeningRooms, contactDetails, cinemaManager);
    }

    private Address createAddress() {
        return new Address(faker.address().streetAddress(), faker.address().buildingNumber(), faker.address().city(), faker.address().zipCode(), faker.address().country());
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

    private Movie createNewMovie() {
        final var productionDetails = createProductionDetails();
        final var description = createDescription();
        final var subtitleAndSoundOptions = createSubtitleAndSoundOptions();
        final var ageRestriction = createAgeRestriction();
        final var movieFile = createMovieFile();
        return new Movie(faker.book().title() + uniqueString(),
                faker.book().title(),
                faker.number().randomDouble(2, 60, 180),
                faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), productionDetails, description, subtitleAndSoundOptions, ageRestriction,
                movieFile, createImages(), createTrailers(), createGenres(), createProjectionTechnologies());
    }

    private ProductionDetails createProductionDetails() {
        final var director = createFilmMember();
        final var actors = createFilmMembers();
        return new ProductionDetails(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), director, actors, createOriginalLanguages(), createProductionCountries());
    }

    private Description createDescription() {
        return new Description(faker.lorem().fixedString(100), faker.lorem().fixedString(100));
    }

    private SubtitleAndSoundOptions createSubtitleAndSoundOptions() {
        return new SubtitleAndSoundOptions(faker.bool().bool(), faker.bool().bool(), faker.bool().bool(), faker.bool().bool());
    }

    private AgeRestriction createAgeRestriction() {
        return AgeRestriction.values()[faker.number().numberBetween(0, AgeRestriction.values().length)];
    }

    private VideoFile createMovieFile() {
        return new VideoFile(faker.lorem().fixedString(10), faker.lorem().fixedString(10), new byte[100]);
    }

    private List<ScreeningTime> createScreeningTimes() {
        final var ScreeningTime = createScreeningTime();
        return List.of(ScreeningTime);
    }

    private CinemaManager createCinemaManager() {
        return new CinemaManager(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(),
                faker.lorem().fixedString(120), Gender.FEMALE);
    }

    private Set<ContactDetails> createContactDetails() {
        Set<ContactDetails> contactDetails = new HashSet<>();
        final var contactType = createContactType();
        contactDetails.add(new ContactDetails(faker.lorem().fixedString(10), contactType));
        return contactDetails;
    }

    private ContactType createContactType() {
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

    private Set<Seat> createSeats() {
        Set<Seat> seats = new HashSet<>();
        for (int i = 0; i < NUMBER_OF_SEATS; i++)
            seats.add(createSeat(i));
        return seats;
    }

    private Seat createSeat(int seatNumber) {
        return new Seat(String.valueOf(seatNumber), faker.lorem().fixedString(1),
                getRandomSeatZone(), getRandomSeatType());
    }

    private Set<ProjectionTechnology> createProjectionTechnologies() {
        Set<ProjectionTechnology> projectionTechnologies = new HashSet<>();
        projectionTechnologies.add(createProjectionTechnology());
        return projectionTechnologies;
    }

    private Image createImage() {
        try {
            File image = new File("src/main/java/org/example/cinemabackend/shared/seeder/images/poster.jpg");
            String name = image.getName();
            String type = Files.probeContentType(image.toPath());
            byte[] data = Files.readAllBytes(image.toPath());
            return new Image(name, type, data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ScreeningTime createScreeningTime() {
        return new ScreeningTime(createScreeningRoom(), LocalDateTime.now().plusHours(2));
    }

    private SeatType getRandomSeatType() {
        return SeatType.values()[new Random().nextInt(SeatType.values().length)];
    }

    private SeatZone getRandomSeatZone() {
        return SeatZone.values()[new Random().nextInt(SeatZone.values().length)];
    }

    private ProjectionTechnology createProjectionTechnology() {
        return new ProjectionTechnology(faker.lorem().fixedString(10) + uniqueString(), faker.lorem().fixedString(100));
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

    private String uniqueString() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}
