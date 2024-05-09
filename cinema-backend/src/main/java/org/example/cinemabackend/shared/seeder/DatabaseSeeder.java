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
    private static final int OBJECTS_TO_SEED = 5;
    private final CinemaSeeder cinemaSeeder;
    private final ProjectionTechnologySeeder projectionTechnologySeeder;
    private final MovieSeeder movieSeeder;

    @Override
    public void run(String... args) {
        if (Arrays.asList(args).contains(SEED_FLAG)) {
            projectionTechnologySeeder.seedDatabase(OBJECTS_TO_SEED);
            System.out.println("Projection technologies seeded");
            movieSeeder.seedDatabase(OBJECTS_TO_SEED);
            System.out.println("Movies seeded");
            cinemaSeeder.seedDatabase(OBJECTS_TO_SEED);
            System.out.println("Cinemas seeded");
        }
    }
}