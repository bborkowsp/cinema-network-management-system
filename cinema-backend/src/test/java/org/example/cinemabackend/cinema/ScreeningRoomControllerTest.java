package org.example.cinemabackend.cinema;

import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.testdata.CinemaTestDataProvider;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.example.cinemabackend.user.testdata.UserTestDataProvider;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(roles = "CINEMA_MANAGER")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScreeningRoomControllerTest {

    private static final String SCREENING_ROOM_ENDPOINT_URL = "/v1/screening-rooms";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CinemaTestDataProvider cinemaTestDataProvider;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void setUp() {
        saveCinemaManagersToDatabase();
        saveCinemasToDatabase();
    }

    private void saveCinemaManagersToDatabase() {
        final var cinemaManagers = UserTestDataProvider.generateSampleCinemaManagers();
        cinemaManagers.forEach(userRepository::save);
    }

    private void saveCinemasToDatabase() {
        List<Cinema> cinemas = cinemaTestDataProvider.generateCinemas();
        cinemas.forEach(cinemaRepository::save);
    }

    @Test
    @Order(1)
    void givenScreeningRoomsInDb_whenGetScreeningRooms_thenStatusIsOkAndScreeningRoomsAreReturned() throws Exception {
        //Given
        final var cinema = cinemaRepository.findAll().getFirst();
        final var cinemaManager = cinema.getCinemaManager();
        loginAsCinemaManager(cinemaManager);

        //When, Then
        mockMvc.perform(get(SCREENING_ROOM_ENDPOINT_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty());
    }

    private void loginAsCinemaManager(User cinemaManager) {
        final var username = cinemaManager.getUsername();
        final var authorities = cinemaManager.getAuthorities();
        final var authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
