package org.example.cinemabackend.cinema;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRepository;
import org.example.cinemabackend.cinema.testdata.CinemaTestDataProvider;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.example.cinemabackend.movie.testdata.MovieTestDataProvider;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(roles = "CINEMA_MANAGER")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScreeningControllerTest {
    private static final String SCREENINGS_ENDPOINT_PATH = "/v1/screenings";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ScreeningRepository screeningRepository;
    @Autowired
    private MovieTestDataProvider movieTestDataProvider;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CinemaTestDataProvider cinemaTestDataProvider;
    @Autowired
    private CinemaRepository cinemaRepository;

    @BeforeAll
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void setUp() {
        saveCinemasToDatabase();
        saveMoviesToDatabase();
    }


    private void saveCinemasToDatabase() {
        final var cinemas = cinemaTestDataProvider.generateCinemas();
        cinemas.forEach(cinemaRepository::save);
    }

    private void saveMoviesToDatabase() {
        final var movies = movieTestDataProvider.generateMovies();
        movies.forEach(movieRepository::save);
    }


    @Test
    @Order(1)
    void givenNoScreeningsInDb_whenGetScreenings_thenStatusIsOkAndEmptyListIsReturned() throws Exception {
        //Given

    }

    @Test
    @Order(2)
    void givenScreeningsInDb_whenGetScreenings_thenStatusIsOkAndScreeningsAreReturned() throws Exception {
        //Given

    }
}
