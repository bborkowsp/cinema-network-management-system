package org.example.cinemabackend.shared.seeder;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.*;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.example.cinemabackend.cinema.infrastructure.adapter.secondary.CinemaJpaRepository;
import org.example.cinemabackend.movie.core.domain.Movie;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
@Order(3)
class CinemaSeeder implements Seeder {
    private static final int NUMBER_OF_SEATS = 255;
    private final CinemaRepository cinemaRepository;
    private final CinemaJpaRepository cinemaJpaRepository;
    private final MovieRepository movieRepository;
    private final ProjectionTechnologyRepository projectionTechnologyRepository;
    private final UserRepository userRepository;
    private final ImageUtil imageUtil;
    private final Faker faker;
    private int increment = 0;

    @Override
    public void seedDatabase(int objectsToSeed) {
        Set<Cinema> cinemas = new HashSet<>();

        while (cinemas.size() < objectsToSeed) {
            final var cinema = createCinema();
            cinemaRepository.save(cinema);
            cinemas.add(cinema);
            increment++;
        }
    }

    private Cinema createCinema() {
        final var address = createAddress();
        final var image = imageUtil.createImage();
        final var screeningRooms = createScreeningRooms();
        final var repertory = createRepertory();
        final var contactDetails = createContactDetails();
        final User cinemaManager;

        if (increment < DatabaseSeeder.OBJECTS_TO_SEED / 2)
            cinemaManager = getCinemaManager();
        else
            cinemaManager = null;

        return new Cinema(
                faker.company().name(),
                faker.lorem().fixedString(100),
                address, image, repertory, screeningRooms, contactDetails, cinemaManager
        );
    }

    private Address createAddress() {
        return new Address(
                faker.address().streetAddress() + " " +
                        faker.address().buildingNumber(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().country()
        );
    }

    private Set<Screening> createRepertory() {
        Set<Screening> repertory = new HashSet<>();
        for (int i = 0; i < 2; i++)
            repertory.add(createScreening());

        repertory.add(createScreeningInSameScreeningRoom(repertory.stream().findAny()));
        return repertory;
    }

    private Screening createScreeningInSameScreeningRoom(Optional<Screening> screening) {
        final var movie = getMovie();
        final var startTime = LocalDateTime.now().plusHours(2);
        final var endTime = startTime.plusMinutes(30);
        final var screeningRoom = screening.get().getScreeningRoom();
        return new Screening(
                movie, startTime, endTime, screeningRoom
        );
    }

    private Screening createScreening() {
        final var movie = getMovie();
        final var startTime = LocalDateTime.now().plusHours(1);
        final var endTime = startTime.plusMinutes(30);
        final var screeningRoom = createScreeningRoom();
        return new Screening(
                movie, startTime, endTime, screeningRoom
        );
    }


    private Movie getMovie() {
        return movieRepository.findAll().get(increment);
    }

    private User getCinemaManager() {
        return this.userRepository.findAllCinemaManagers().get(increment);
    }

    private Set<ContactDetails> createContactDetails() {
        Set<ContactDetails> contactDetails = new HashSet<>();
        final var contactType = createContactType();
        contactDetails.add(new ContactDetails(
                faker.lorem().fixedString(10),
                contactType
        ));
        return contactDetails;
    }

    private ContactType createContactType() {
        return new ContactType(
                "+48 123 123 123",
                faker.internet().emailAddress()
        );
    }

    private Set<ScreeningRoom> createScreeningRooms() {
        Set<ScreeningRoom> screeningRooms = new HashSet<>();
        screeningRooms.add(createScreeningRoom());
        return screeningRooms;
    }

    private ScreeningRoom createScreeningRoom() {
        final var seatRows = createSeats();
        final var projectionTechnologies = getProjectionTechnologies();
        return new ScreeningRoom(
                faker.lorem().fixedString(10),
                seatRows, projectionTechnologies
        );
    }

    private List<SeatRow> createSeats() {
        List<SeatRow> seatRows = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            seatRows.add(new SeatRow(createSeatRow()));
        }
        return seatRows;
    }

    private List<Seat> createSeatRow() {
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_SEATS; i++) {
            seats.add(createSeat(i));
        }
        return seats;
    }

    private Seat createSeat(int seatNumber) {
        return new Seat(
                seatNumber,
                seatNumber % 10,
                getRandomSeatZone(),
                getRandomSeatType()
        );
    }

    Set<ProjectionTechnology> getProjectionTechnologies() {
        return Set.of(
                projectionTechnologyRepository.findAll().getFirst(),
                projectionTechnologyRepository.findAll().getLast()
        );
    }


    private SeatType getRandomSeatType() {
        return SeatType.values()[new Random().nextInt(SeatType.values().length)];
    }

    private SeatZone getRandomSeatZone() {
        return SeatZone.values()[new Random().nextInt(SeatZone.values().length)];
    }

}
