package org.example.cinemabackend._shared.seeder;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.*;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
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
    private static final int NUMBER_OF_SCREENING_ROOMS = 3;
    private static final int NUMBER_OF_ROWS_IN_SCREENING_ROOM = 1;
    private static final int NUMBER_OF_COLUMNS_IN_SCREENING_ROOM = 1;
    private final CinemaRepository cinemaRepository;
    private final UserRepository userRepository;
    private final Faker faker;
    private int increment = 0;

    @Override
    public void seedDatabase(int objectsToSeed) {
        Set<Cinema> cinemas = new HashSet<>();
        while (cinemas.size() < objectsToSeed) {
            final var cinema = createCinema();
            cinema.setImage("cinema.jpg");
            if (!cinemas.contains(cinema)) {
                cinemas.add(cinema);
                cinemaRepository.save(cinema);
                increment++;
            }
        }
    }

    private Cinema createCinema() {
        final var address = createAddress();
        final var screeningRooms = createScreeningRooms();
        final var contactDetails = createContactDetails();
        User cinemaManager = null;

        if (increment < DatabaseSeeder.OBJECTS_TO_SEED / 2) cinemaManager = getCinemaManager();

        Cinema cinema;
        if (cinemaManager == null) {
            cinema = new Cinema(
                    faker.company().name(),
                    faker.lorem().fixedString(100),
                    address
            );
        } else {
            cinema = new Cinema(
                    faker.company().name(),
                    faker.lorem().fixedString(100),
                    address,
                    cinemaManager
            );
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

    private Set<ScreeningRoom> createScreeningRooms() {
        Set<ScreeningRoom> screeningRooms = new HashSet<>();
        while (screeningRooms.size() < NUMBER_OF_SCREENING_ROOMS) {
            screeningRooms.add(createScreeningRoom());
        }
        return screeningRooms;
    }

    private ScreeningRoom createScreeningRoom() {
        return new ScreeningRoom(
                faker.lorem().fixedString(10),
                createSeatRows()
        );
    }

    private List<SeatRow> createSeatRows() {
        List<SeatRow> seatRows = new ArrayList<>();
        for (int row = 0; row < NUMBER_OF_ROWS_IN_SCREENING_ROOM; row++) {
            seatRows.add(createSeatRow(row));
        }
        return seatRows;
    }

    private SeatRow createSeatRow(int row) {
        List<Seat> seats = new ArrayList<>();
        for (int column = 0; column < NUMBER_OF_COLUMNS_IN_SCREENING_ROOM; column++) {
            seats.add(createSeat(row, column));
        }
        return new SeatRow(seats);
    }

    private Seat createSeat(int seatRow, int seatColumn) {
        return new Seat(
                seatRow,
                seatColumn,
                getRandomSeatZone(),
                List.of(new SeatStatus(getRandomSeatStatus(), LocalDateTime.now(), LocalDateTime.now().plusHours(1)))
        );
    }

    private SeatZone getRandomSeatZone() {
        return SeatZone.values()[new Random().nextInt(SeatZone.values().length)];
    }

    private Status getRandomSeatStatus() {
        return Status.values()[new Random().nextInt(Status.values().length)];
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

    private User getCinemaManager() {
        return this.userRepository.findAllCinemaManagers().get(increment);
    }
}
