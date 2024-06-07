package org.example.cinemabackend.cinema.testdata;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.shared.seeder.ImageUtil;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.example.cinemabackend.user.testdata.UserTestDataProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.example.cinemabackend.cinema.testdata.AddressTestDataProvider.generateSampleAddresses;

@Component
@RequiredArgsConstructor
public class CinemaTestDataProvider {

    private final UserRepository userRepository;

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
}
