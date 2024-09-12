package org.example.cinemabackend.cinema.testdata;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateCinemaRequest;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.primary.UserMapper;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.example.cinemabackend.user.testdata.UserTestDataProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.example.cinemabackend.cinema.testdata.AddressTestDataProvider.*;
import static org.example.cinemabackend.cinema.testdata.ContactDetailTestDataProvider.generateContactDetailRequests;
import static org.example.cinemabackend.cinema.testdata.ContactDetailTestDataProvider.generateContactDetails;
import static org.example.cinemabackend.user.testdata.UserTestDataProvider.generateSampleCinemaManager;

@Component
@RequiredArgsConstructor
public class CinemaTestDataProvider {

    private static final String CINEMA_IMAGE = "cinema.jpg";
    private static final String CINEMA_NAME = "Cinema Name";
    private static final String CINEMA_DESCRIPTION = "Cinema Description";
    private static final int NUMBER_OF_CINEMAS_TO_GENERATE = 3;
    private static int cinemaCounter = 0;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ScreeningRoomTestDataProvider screeningRoomTestDataProvider;

    public List<Cinema> generateSampleCinemas() {
        List<Cinema> cinemas = new ArrayList<>();
        final var cinemaManagers = UserTestDataProvider.generateSampleCinemaManagers();
        saveCinemaManagersToDatabase(cinemaManagers);

        for (int i = 0; i < NUMBER_OF_CINEMAS_TO_GENERATE; i++) {
            final var cinema = generateCinema();
            cinemas.add(cinema);
            cinemaCounter++;
        }
        return cinemas;
    }

    private void saveCinemaManagersToDatabase(List<User> cinemaManagers) {
        cinemaManagers.forEach(userRepository::save);
    }

    private Cinema generateCinema() {
        return new Cinema(
                CINEMA_NAME + cinemaCounter,
                CINEMA_DESCRIPTION,
                generateAddress(),
                CINEMA_IMAGE,
                screeningRoomTestDataProvider.generateSampleScreeningRooms(),
                generateContactDetails(),
                getCinemaManager()
        );
    }

    private User getCinemaManager() {
        return this.userRepository.findAllCinemaManagers().get(cinemaCounter);
    }

    public CreateCinemaRequest generateCreateCinemaRequest() {
        final var createAddressRequest = generateCreateAddressRequest();

        final var cinemaManager = generateSampleCinemaManager();
        saveCinemaManagersToDatabase(List.of(cinemaManager));

        final var userResponse = userMapper.mapUserToUserResponse(cinemaManager);
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

        final var cinemaManager = generateSampleCinemaManager();
        saveCinemaManagersToDatabase(List.of(cinemaManager));

        final var screeningRooms = screeningRoomTestDataProvider.generateCreateScreeningRoomRequests();
        final var contactDetails = generateContactDetailRequests();
        final var userResponse = userMapper.mapUserToUserResponse(cinemaManager);

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
