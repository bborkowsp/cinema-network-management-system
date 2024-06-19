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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Faker faker;
    private int increment = 0;

    @Override
    public void seedDatabase(int objectsToSeed) {
        createUsers(objectsToSeed, Role.CINEMA_MANAGER, "cinemaMgr");
        increment = 0;
        createUsers(objectsToSeed, Role.CINEMA_NETWORK_MANAGER, "cinemaNetMgr");
        createAdmin();
    }

    private void createAdmin() {
        final var user = createUser(Role.ADMIN, "admin@admin.com");
        userRepository.save(user);
    }

    private void createUsers(int objectsToSeed, Role role, String emailPrefix) {
        Set<User> users = new HashSet<>();
        while (users.size() < objectsToSeed) {
            final var email = emailPrefix + increment + "@email.com";
            final var user = createUser(role, email);
            userRepository.save(user);
            users.add(user);
            increment++;
        }
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