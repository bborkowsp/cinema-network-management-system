package org.example.cinemabackend._shared.seeder;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.core.domain.Role;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(2)
public class UserSeeder implements Seeder {
    private static final String PASSWORD = "password";
    private static final String EMAIL_DOMAIN = "@email.com";
    private static final String CINEMA_MANAGER_EMAIL_USERNAME = "cinemaMgr";
    private static final String CINEMA_NETWORK_MANAGER_EMAIL_USERNAME = "cinemaNetMgr";
    private static final String ADMIN_EMAIL = "admin" + EMAIL_DOMAIN;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Faker faker;
    private int increment = 0;

    @Override
    public void seedDatabase(int objectsToSeed) {
        createUsers(objectsToSeed, Role.CINEMA_MANAGER, CINEMA_MANAGER_EMAIL_USERNAME);
        createUsers(objectsToSeed, Role.CINEMA_NETWORK_MANAGER, CINEMA_NETWORK_MANAGER_EMAIL_USERNAME);
        createAdmin();
    }

    private void createUsers(int objectsToSeed, Role role, String emailPrefix) {
        Set<User> users = new HashSet<>();
        while (users.size() < objectsToSeed) {
            final var email = emailPrefix + increment + EMAIL_DOMAIN;
            final var user = createUser(role, email);
            userRepository.save(user);
            users.add(user);
            increment++;
        }
        increment = 0;
    }

    private void createAdmin() {
        final var user = createUser(Role.ADMIN, ADMIN_EMAIL);
        userRepository.save(user);
    }

    private User createUser(Role role, String email) {
        final var encodedPassword = passwordEncoder.encode(PASSWORD);
        return new User(
                faker.name().firstName(),
                faker.name().lastName(),
                email,
                encodedPassword,
                role
        );
    }
}