package org.example.cinemabackend._shared.seeder;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
@Transactional
@RequiredArgsConstructor
class DatabaseSeeder implements CommandLineRunner {
    static final int OBJECTS_TO_SEED = 20;
    private static final Logger LOGGER = LogManager.getLogger(DatabaseSeeder.class);
    private static final String SEED_FLAG = "--seed";
    private final CinemaSeeder cinemaSeeder;
    private final UserSeeder userSeeder;
    private final MovieSeeder movieSeeder;
    private final ScreeningSeeder screeningSeeder;

    @Override
    public void run(String... args) {
        if (Arrays.asList(args).contains(SEED_FLAG)) {
            movieSeeder.seedDatabase(OBJECTS_TO_SEED);
            LOGGER.info("Movies seeded");

            userSeeder.seedDatabase(OBJECTS_TO_SEED / 2);
            LOGGER.debug("Users seeded");

            cinemaSeeder.seedDatabase(OBJECTS_TO_SEED);
            LOGGER.info("Cinemas seeded");

            screeningSeeder.seedDatabase(OBJECTS_TO_SEED);
            LOGGER.info("Screenings seeded");
        }
    }
}
