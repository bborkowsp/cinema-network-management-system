package org.example.cinemabackend.shared.seeder;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
@Transactional
@RequiredArgsConstructor
class DatabaseSeeder implements CommandLineRunner {
    private static final String SEED_FLAG = "--seed";
    private static final int OBJECTS_TO_SEED = 12;
    private final CinemaSeeder cinemaSeeder;

    @Override
    public void run(String... args) {
        if (Arrays.asList(args).contains(SEED_FLAG)) {
            cinemaSeeder.seedDatabase(OBJECTS_TO_SEED);
        }
    }
}