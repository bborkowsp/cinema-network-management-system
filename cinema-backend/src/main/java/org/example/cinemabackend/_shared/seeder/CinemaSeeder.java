package org.example.cinemabackend._shared.seeder;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.*;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(3)
class CinemaSeeder implements Seeder {
    private static final int NUMBER_OF_SCREENING_ROOMS = 3;
    private final CinemaRepository cinemaRepository;
    private final ProjectionTechnologyRepository projectionTechnologyRepository;
    private final UserRepository userRepository;
    private final Faker faker;
    private int increment = 0;

    @Override
    public void seedDatabase(int objectsToSeed) {
        Set<Cinema> cinemas = new HashSet<>();
        while (cinemas.size() < objectsToSeed) {
            final var cinema = createCinema();
            if (!cinemas.contains(cinema)) {
                cinemas.add(cinema);
                cinemaRepository.save(cinema);
                increment++;
            }
        }
    }

    private Cinema createCinema() {
        final var address = createAddress();
        final var image = ImageUtil.createImage();
        final var screeningRooms = createScreeningRooms();
        final var contactDetails = createContactDetails();
        User cinemaManager = null;

        if (increment < DatabaseSeeder.OBJECTS_TO_SEED / 2)
            cinemaManager = getCinemaManager();

        Cinema cinema;
        if (cinemaManager == null) {
            cinema = new Cinema(
                    faker.company().name(),
                    faker.lorem().fixedString(100),
                    address, image);
        } else {
            cinema = new Cinema(
                    faker.company().name(),
                    faker.lorem().fixedString(100),
                    address, image, cinemaManager);
        }

        screeningRooms.forEach(cinema::addScreeningRoom);
        contactDetails.forEach(cinema::addContactDetails);
        return cinema;
    }

    private Address createAddress() {
        return new Address(
                faker.address().streetAddress() + " " + faker.address().buildingNumber(),
                faker.address().city(),
                "00-000",
                faker.address().country()
        );
    }

    private User getCinemaManager() {
        return this.userRepository.findAllCinemaManagers().get(increment);
    }

    private Set<ContactDetails> createContactDetails() {
        Set<ContactDetails> contactDetails = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            final var contactType = createContactType();
            contactDetails.add(new ContactDetails(
                    faker.lorem().fixedString(10),
                    contactType
            ));
        }
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
        while (screeningRooms.size() < NUMBER_OF_SCREENING_ROOMS) {
            screeningRooms.add(createScreeningRoom());
        }
        return screeningRooms;
    }

    private ScreeningRoom createScreeningRoom() {
        final var seats = createSeats();
        final var projectionTechnologies = getProjectionTechnologies();
        return new ScreeningRoom(
                faker.lorem().fixedString(10),
                seats,
                projectionTechnologies
        );
    }

    private Seat[][] createSeats() {
        Seat[][] seats = new Seat[3][6];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++)
                seats[i][j] = createSeat(i, j);
        }
        return seats;
    }


    private Seat createSeat(int seatRow, int seatColumn) {
        return new Seat(
                seatRow,
                seatColumn,
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
