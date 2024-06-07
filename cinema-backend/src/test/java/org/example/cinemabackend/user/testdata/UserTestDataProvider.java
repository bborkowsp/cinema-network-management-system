package org.example.cinemabackend.user.testdata;

import org.example.cinemabackend.user.core.domain.Role;
import org.example.cinemabackend.user.core.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserTestDataProvider {

    public static List<User> generateSampleCinemaManagers() {
        List<User> users = new ArrayList<>();

        String[] usernames = {"Username 1", "Username 2", "Username 3"};
        String[] passwords = {"Password 1", "Password 2", "Password 3"};
        String[] emails = {"Email 1", "Email 2", "Email 3"};
        String[] roles = {"Role 1", "Role 2", "Role 3"};

        for (int i = 0; i < usernames.length; i++) {
            users.add(new User(usernames[i], passwords[i], emails[i], roles[i], Role.CINEMA_MANAGER));
        }

        return users;
    }
}
