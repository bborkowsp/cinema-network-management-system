package org.example.cinemabackend.shared.seeder;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.core.domain.Role;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(2)
public class UserSeeder implements Seeder {

    private final UserRepository userRepository;
    private final Faker faker;
    private int increment = 0;

    @Override
    public void seedDatabase(int objectsToSeed) {
        Set<User> users = new HashSet<>();
        while (users.size() < objectsToSeed) {
            final var user = createUser();
            userRepository.save(user);
            users.add(user);
            increment++;
        }
    }

    private User createUser() {
        return new User(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress() + increment,
                faker.internet().password(),
                Role.CINEMA_MANAGER
        );
    }
}