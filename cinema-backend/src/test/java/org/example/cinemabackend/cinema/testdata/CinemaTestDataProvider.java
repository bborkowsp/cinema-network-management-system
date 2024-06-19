package org.example.cinemabackend.cinema.testdata;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend._shared.seeder.ImageUtil;
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
import static org.example.cinemabackend.cinema.testdata.ImageTestDataProvider.generateCreateImageRequest;
import static org.example.cinemabackend.cinema.testdata.ImageTestDataProvider.generateUpdateImageRequest;
import static org.example.cinemabackend.user.testdata.UserTestDataProvider.generateSampleCinemaManager;

@Component
@RequiredArgsConstructor
public class CinemaTestDataProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ScreeningRoomTestDataProvider screeningRoomTestDataProvider;

    public List<Cinema> generateSampleCinemas() {
        List<Cinema> cinemas = new ArrayList<>();

        String[] names = {"Name 1", "Name 2", "Name 3"};
        String[] descriptions = {"Description 1", "Description 2", "Description 3"};
        final var addresses = generateSampleAddresses();
        final var image = ImageUtil.createImage();

        final var cinemaManagers = UserTestDataProvider.generateSampleCinemaManagers();
        saveCinemaManagersToDatabase(cinemaManagers);

        for (int i = 0; i < names.length; i++) {
            cinemas.add(new Cinema(names[i], descriptions[i], addresses.get(i), image, getCinemaManager(i)));
        }

        return cinemas;
    }

    private void saveCinemaManagersToDatabase(List<User> cinemaManagers) {
        cinemaManagers.forEach(userRepository::save);
    }

    private User getCinemaManager(int index) {
        return this.userRepository.findAllCinemaManagers().get(index);
    }

    public CreateCinemaRequest generateCreateCinemaRequest() {
        final var createAddressRequest = generateCreateAddressRequest();
        final var createImageRequest = generateCreateImageRequest();

        final var cinemaManager = generateSampleCinemaManager();
        saveCinemaManagersToDatabase(List.of(cinemaManager));

        final var userResponse = userMapper.mapUserToUserResponse(cinemaManager);
        final var screeningRooms = screeningRoomTestDataProvider.generateCreateScreeningRoomRequests();
        final var contactDetails = generateContactDetailRequests();
        return CreateCinemaRequest.builder()
                .name("Name")
                .description("Description")
                .address(createAddressRequest)
                .image(createImageRequest)
                .screeningRooms(screeningRooms)
                .contactDetails(contactDetails)
                .cinemaManager(userResponse)
                .build();
    }

    public UpdateCinemaRequest generateUpdateCinemaRequest(String name) {
        final var updateAddressRequest = generateUpdateAddressRequest();
        final var updateImageRequest = generateUpdateImageRequest();

        final var cinemaManager = generateSampleCinemaManager();
        saveCinemaManagersToDatabase(List.of(cinemaManager));

        final var screeningRooms = screeningRoomTestDataProvider.generateCreateScreeningRoomRequests();
        final var contactDetails = generateContactDetailRequests();
        final var userResponse = userMapper.mapUserToUserResponse(cinemaManager);

        return UpdateCinemaRequest.builder()
                .name(name)
                .description("Description")
                .address(updateAddressRequest)
                .image(updateImageRequest)
                .screeningRooms(screeningRooms)
                .contactDetails(contactDetails)
                .cinemaManager(userResponse)
                .build();

    }


}
