package org.example.cinemabackend.cinema;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRepository;
import org.example.cinemabackend.cinema.testdata.CinemaTestDataProvider;
import org.example.cinemabackend.cinema.testdata.ScreeningTestDataProvider;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.example.cinemabackend.movie.testdata.MovieTestDataProvider;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.example.cinemabackend.user.testdata.UserTestDataProvider;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    @Autowired
    private ScreeningTestDataProvider screeningTestDataProvider;
    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void setUp() {
        saveCinemaManagersToDatabase();
        saveCinemasToDatabase();
        saveMoviesToDatabase();
    }

    private void saveCinemaManagersToDatabase() {
        final var cinemaManagers = UserTestDataProvider.generateSampleCinemaManagers();
        cinemaManagers.forEach(userRepository::save);
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
    void givenNoScreeningsInDb_whenGetRepertoryByCinemaAndDate_thenStatusIsOkAndEmptyListIsReturned() throws Exception {
        //Given
        final var cinemaName = cinemaRepository.findAll().getFirst().getName();
        final var date = LocalDate.now();

        //When, Then
        final var url = SCREENINGS_ENDPOINT_PATH + "/repertory/" + cinemaName + "/" + date;
        mockMvc.perform(get(url))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isEmpty(),
                        jsonPath("$.content").isArray()
                );
    }

    // getScreeningsDetails test
    @Test
    @Order(2)
    void givenScreeningsInDb_whenGetScreeningsDetails_thenStatusIsOkAndScreeningsAreReturned() throws Exception {
        //Given
        final var movie = movieRepository.findAll().getFirst();
        final var date = LocalDate.now();

        //When, Then
        final var url = SCREENINGS_ENDPOINT_PATH + "/details/" + movie.getTitle() + "/" + date;
        mockMvc.perform(get(url))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.movie").isNotEmpty(),
                        jsonPath("$.movie.title").value(movie.getTitle()),
                        jsonPath("$.movie.duration").value(movie.getDuration()),
                        jsonPath("$.movie.description").value(movie.getDescription()),
                        jsonPath("$.movie.poster").value(movie.getPoster()),
                        jsonPath("$.movie.productionDetails").isNotEmpty(),
                        jsonPath("$.movie.ageRestriction").value(movie.getAgeRestriction().name()),
                        jsonPath("$.movie.trailer").value(movie.getTrailer()),
                        jsonPath("$.movie.genres").isNotEmpty(),
                        jsonPath("$.movie.movieVariants").isNotEmpty(),
                        jsonPath("$.screenings").isMap()
                );
    }

    // getScreenings test
    @Test
    @Order(3)
    void givenNoScreeningsInDb_whenGetScreenings_thenStatusIsOkAndEmptyListIsReturned() throws Exception {
        //Given
        final var cinema = cinemaRepository.findAll().getFirst();
        final var cinemaManager = cinema.getCinemaManager();
        loginAsCinemaManager(cinemaManager);

        //When, Then
        mockMvc.perform(get(SCREENINGS_ENDPOINT_PATH))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isEmpty(),
                        jsonPath("$.content").isArray()
                );
    }

    private void loginAsCinemaManager(User cinemaManager) {
        final var username = cinemaManager.getUsername();
        final var authorities = cinemaManager.getAuthorities();
        final var authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    @Order(4)
    void givenNoScreeningsInDb_whenGetScreening_thenStatusIsNotFound() throws Exception {
        //Given
        final var cinema = cinemaRepository.findAll().getFirst();
        final var cinemaManager = cinema.getCinemaManager();
        loginAsCinemaManager(cinemaManager);

        //When, Then
        final var url = SCREENINGS_ENDPOINT_PATH + "/id/1";
        mockMvc.perform(get(url))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    void givenNoScreeningsInDb_whenCreateScreening_thenStatusIsCreated() throws Exception {
        //Given
        final var cinema = cinemaRepository.findAll().getFirst();
        System.out.println(cinema.getName());
        final var cinemaManager = cinema.getCinemaManager();
        loginAsCinemaManager(cinemaManager);
        final var createScreeningRequest = screeningTestDataProvider.generateCreateScreeningRequest();
        assertThat(screeningRepository.findAll()).isEmpty();

        //When
        mockMvc.perform(post(SCREENINGS_ENDPOINT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createScreeningRequest)))
                .andExpect(status().isCreated());

        //Then
        mockMvc.perform(get(SCREENINGS_ENDPOINT_PATH))
                .andExpect(jsonPath("$.content").isNotEmpty());

        final var screening = screeningRepository.findAll().getFirst();
        assertAll(
                () -> assertEquals(createScreeningRequest.movieTitle(), screening.getMovie().getTitle()),
                () -> assertEquals(createScreeningRequest.startTime(), screening.getStartTime()),
                () -> assertEquals(createScreeningRequest.endTime(), screening.getEndTime()),
                () -> assertEquals(createScreeningRequest.movieVariant().projectionTechnology(), screening.getMovieVariant().getProjectionTechnology()),
                () -> assertEquals(createScreeningRequest.movieVariant().language(), screening.getMovieVariant().getLanguage())
        );
    }

    @Test
    @Order(7)
    void givenScreeningInDb_whenGetRepertoryByCinemaAndDate_thenStatusIsOkAndScreeningIsReturned() throws Exception {
        //Given
        final var screening = screeningRepository.findAll().getFirst();
        final var cinema = cinemaRepository.findAll().getFirst();

        //When, Then
        final var url = SCREENINGS_ENDPOINT_PATH + "/repertory/" + cinema.getName() + "/" + ScreeningTestDataProvider.startTime.toLocalDate();
        mockMvc.perform(get(url))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isNotEmpty(),
                        jsonPath("$.content[0].id").value(screening.getId()),
                        jsonPath("$.content[0].startTime").value(screening.getStartTime().toString()),
                        jsonPath("$.content[0].endTime").value(screening.getEndTime().toString()),
                        jsonPath("$.content[0].movie.title").value(screening.getMovie().getTitle()),
                        jsonPath("$.content[0].movieVariant.projectionTechnology").value(screening.getMovieVariant().getProjectionTechnology().toString()),
                        jsonPath("$.content[0].movieVariant.language").value(screening.getMovieVariant().getLanguage().toString())
                );
    }

    @Test
    @Order(8)
    void givenScreeningInDb_whenGetScreeningDetails_thenStatusIsOkAndScreeningIsReturned() throws Exception {
        //Given
        final var screening = screeningRepository.findAll().getFirst();
        final var cinema = cinemaRepository.findAll().getFirst();

        //When, Then
        final var url = SCREENINGS_ENDPOINT_PATH + "/details/" + screening.getMovie().getTitle() + "/" + ScreeningTestDataProvider.startTime.toLocalDate();
        mockMvc.perform(get(url))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.movie").isNotEmpty(),
                        jsonPath("$.movie.title").value(screening.getMovie().getTitle()),
                        jsonPath("$.movie.duration").value(screening.getMovie().getDuration()),
                        jsonPath("$.movie.description").value(screening.getMovie().getDescription()),
                        jsonPath("$.movie.poster").value(screening.getMovie().getPoster()),
                        jsonPath("$.movie.productionDetails").isNotEmpty(),
                        jsonPath("$.movie.ageRestriction").value(screening.getMovie().getAgeRestriction().name()),
                        jsonPath("$.movie.trailer").value(screening.getMovie().getTrailer()),
                        jsonPath("$.movie.genres").isNotEmpty(),
                        jsonPath("$.movie.movieVariants").isNotEmpty(),
                        jsonPath("$.screenings").isMap(),
                        jsonPath("$.screenings['" + cinema.getName() + "']").isNotEmpty(),
                        jsonPath("$.screenings['" + cinema.getName() + "'][0].id").value(screening.getId()),
                        jsonPath("$.screenings['" + cinema.getName() + "'][0].startTime").isNotEmpty(),
                        jsonPath("$.screenings['" + cinema.getName() + "'][0].endTime").isNotEmpty(),
                        jsonPath("$.screenings['" + cinema.getName() + "'][0].movie.title").value(screening.getMovie().getTitle()),
                        jsonPath("$.screenings['" + cinema.getName() + "'][0].movieVariant.projectionTechnology").value(screening.getMovieVariant().getProjectionTechnology().toString()),
                        jsonPath("$.screenings['" + cinema.getName() + "'][0].movieVariant.language").value(screening.getMovieVariant().getLanguage().toString())
                );
    }

    @Test
    @Order(9)
    void givenScreeningInDb_whenGetScreenings_thenStatusIsOkAndScreeningIsReturned() throws Exception {
        //Given
        final var screening = screeningRepository.findAll().getFirst();
        final var cinema = cinemaRepository.findAll().getFirst();
        final var cinemaManager = cinema.getCinemaManager();
        loginAsCinemaManager(cinemaManager);

        //When, Then
        mockMvc.perform(get(SCREENINGS_ENDPOINT_PATH))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isNotEmpty(),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content[0].id").value(screening.getId()),
                        jsonPath("$.content[0].startTime").value(screening.getStartTime().toString()),
                        jsonPath("$.content[0].endTime").value(screening.getEndTime().toString()),
                        jsonPath("$.content[0].movie.title").value(screening.getMovie().getTitle()),
                        jsonPath("$.content[0].movieVariant.projectionTechnology").value(screening.getMovieVariant().getProjectionTechnology().toString()),
                        jsonPath("$.content[0].movieVariant.language").value(screening.getMovieVariant().getLanguage().toString())
                );
    }

    @Test
    @Order(10)
    void givenScreeningInDb_whenGetScreening_thenStatusIsOkAndScreeningIsReturned() throws Exception {
        //Given
        final var screening = screeningRepository.findAll().getFirst();
        final var cinema = cinemaRepository.findAll().getFirst();
        final var cinemaManager = cinema.getCinemaManager();
        loginAsCinemaManager(cinemaManager);

        //When, Then
        mockMvc.perform(get(SCREENINGS_ENDPOINT_PATH + "/id/" + screening.getId()))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(screening.getId()),
                        jsonPath("$.startTime").value(screening.getStartTime().toString()),
                        jsonPath("$.endTime").value(screening.getEndTime().toString()),
                        jsonPath("$.movie.title").value(screening.getMovie().getTitle()),
                        jsonPath("$.movieVariant.projectionTechnology").value(screening.getMovieVariant().getProjectionTechnology().toString()),
                        jsonPath("$.movieVariant.language").value(screening.getMovieVariant().getLanguage().toString())
                );
    }

    @Test
    @Order(11)
    void givenScreeningInDb_whenUpdateScreening_thenStatusIsNoContent() throws Exception {
        //Given
        final var screening = screeningRepository.findAll().getFirst();
        final var cinema = cinemaRepository.findAll().getFirst();
        final var cinemaManager = cinema.getCinemaManager();
        loginAsCinemaManager(cinemaManager);

    }

    @Test
    @Order(12)
    void givenScreeningInDb_whenDeleteScreening_thenStatusIsNoContent() throws Exception {
        //Given
        final var screening = screeningRepository.findAll().getFirst();
        final var cinema = cinemaRepository.findAll().getFirst();
        final var cinemaManager = cinema.getCinemaManager();
        loginAsCinemaManager(cinemaManager);

        //When
        mockMvc.perform(delete(SCREENINGS_ENDPOINT_PATH + "/" + screening.getId()))
                .andExpect(status().isNoContent());

        //Then
        mockMvc.perform(get(SCREENINGS_ENDPOINT_PATH + "/id/" + screening.getId()))
                .andExpect(status().isNotFound());
        assertThat(screeningRepository.findAll()).isEmpty();
    }
}
