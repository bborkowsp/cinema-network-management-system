package org.example.cinemabackend.user.testdata;

import org.example.cinemabackend.user.core.domain.Role;
import org.example.cinemabackend.user.core.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserTestDataProvider {
    private static final int NUMBER_OF_USERS_TO_GENERATE = 3;
    private static final String EMAIL = "email@example.com";
    private static final String FIRST_NAME = "First Name";
    private static final String LAST_NAME = "Last Name";
    private static final String PASSWORD = "Password";
    private static int userCounter = 0;

    public static List<User> generateSampleCinemaManagers() {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_USERS_TO_GENERATE; i++) {
            final var movie = generateSampleCinemaManager();
            users.add(movie);
        }
        return users;
    }

    public static User generateSampleCinemaManager() {
        userCounter++;
        return new User(
                FIRST_NAME,
                LAST_NAME,
                EMAIL + userCounter,
                PASSWORD,
                Role.CINEMA_MANAGER
        );
    }
}
