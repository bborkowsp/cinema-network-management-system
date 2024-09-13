package org.example.cinemabackend.cinema.testdata;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateCinemaRequest;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.primary.UserMapper;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.example.cinemabackend.cinema.testdata.AddressTestDataProvider.*;
import static org.example.cinemabackend.cinema.testdata.ContactDetailTestDataProvider.generateContactDetailRequests;
import static org.example.cinemabackend.cinema.testdata.ContactDetailTestDataProvider.generateContactDetails;

@Component
@RequiredArgsConstructor
public class CinemaTestDataProvider {
    private static final String CINEMA_IMAGE = "cinema-test.jpg";
    private static final String CINEMA_NAME = "Cinema Name";
    private static final String CINEMA_DESCRIPTION = "Cinema Description";
    private static final int NUMBER_OF_CINEMAS_TO_GENERATE = 3;
    private static int cinemaCounter = 0;
    private static int assignedCinemaManagerCounter = -1;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ScreeningRoomTestDataProvider screeningRoomTestDataProvider;

    public List<Cinema> generateCinemas() {
        List<Cinema> cinemas = new ArrayList<>();
        while (cinemas.size() < NUMBER_OF_CINEMAS_TO_GENERATE) {
            final var cinema = generateCinema();
            cinemas.add(cinema);
            cinemaCounter++;
        }
        return cinemas;
    }

    private Cinema generateCinema() {
        Cinema cinema = new Cinema(
                CINEMA_NAME + cinemaCounter,
                CINEMA_DESCRIPTION,
                generateAddress(),
                getCinemaManager()
        );
        final var screeningRooms = screeningRoomTestDataProvider.generateScreeningRooms();
        screeningRooms.forEach(cinema::addScreeningRoom);
        final var contactDetails = generateContactDetails();
        contactDetails.forEach(cinema::addContactDetails);
        cinema.setImage(CINEMA_IMAGE);
        return cinema;
    }

    private User getCinemaManager() {
        assignedCinemaManagerCounter++;
        return this.userRepository.findAllCinemaManagers().get(assignedCinemaManagerCounter);
    }

    public CreateCinemaRequest generateCreateCinemaRequest() {
        final var createAddressRequest = generateCreateAddressRequest();
        final var userResponse = userMapper.mapUserToUserResponse(getCinemaManager());
        final var screeningRooms = screeningRoomTestDataProvider.generateCreateScreeningRoomRequests();
        final var contactDetails = generateContactDetailRequests();
        return CreateCinemaRequest.builder()
                .name("Name")
                .description("Description")
                .address(createAddressRequest)
                .screeningRooms(screeningRooms)
                .contactDetails(contactDetails)
                .cinemaManager(userResponse)
                .build();
    }


    public UpdateCinemaRequest generateUpdateCinemaRequest(String name) {
        final var updateAddressRequest = generateUpdateAddressRequest();
        final var screeningRooms = screeningRoomTestDataProvider.generateCreateScreeningRoomRequests();
        final var contactDetails = generateContactDetailRequests();
        final var userResponse = userMapper.mapUserToUserResponse(getCinemaManager());

        return UpdateCinemaRequest.builder()
                .name(name)
                .description("Description")
                .address(updateAddressRequest)
                .screeningRooms(screeningRooms)
                .contactDetails(contactDetails)
                .cinemaManager(userResponse)
                .build();
    }
}
