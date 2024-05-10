package org.example.cinemabackend.shared.seeder;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
@Transactional
@RequiredArgsConstructor
class DatabaseSeeder implements CommandLineRunner {
    private static final String SEED_FLAG = "--seed";
    private static final int OBJECTS_TO_SEED = 3;
    private final CinemaSeeder cinemaSeeder;
    private final ProjectionTechnologySeeder projectionTechnologySeeder;
    private final UserSeeder userSeeder;
    private final MovieSeeder movieSeeder;

    private final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    @Override
    public void run(String... args) {
        if (Arrays.asList(args).contains(SEED_FLAG)) {
            projectionTechnologySeeder.seedDatabase(OBJECTS_TO_SEED);
            logger.info("Projection technologies seeded");

            movieSeeder.seedDatabase(OBJECTS_TO_SEED);
            logger.info("Movies seeded");

            userSeeder.seedDatabase(OBJECTS_TO_SEED);
            logger.info("Users seeded");

            cinemaSeeder.seedDatabase(OBJECTS_TO_SEED);
            logger.info("Cinemas seeded");
        }
    }
}
