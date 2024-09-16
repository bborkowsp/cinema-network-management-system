package org.example.cinemabackend.cinema;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.testdata.CinemaTestDataProvider;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.example.cinemabackend.movie.testdata.MovieTestDataProvider;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.example.cinemabackend.user.testdata.UserTestDataProvider;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(roles = "CINEMA_NETWORK_MANAGER")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CinemaControllerTest {

    private static final String CINEMAS_ENDPOINT_PATH = "/v1/cinemas";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CinemaTestDataProvider cinemaTestDataProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieTestDataProvider movieTestDataProvider;

    @Autowired
    private MovieRepository movieRepository;

    @BeforeAll
    void setUp() {
        saveCinemaManagersToDatabase();
        saveMoviesToDatabase();
    }

    private void saveCinemaManagersToDatabase() {
        final var cinemaManagers = UserTestDataProvider.generateSampleCinemaManagers();
        cinemaManagers.forEach(userRepository::save);
    }

    private void saveMoviesToDatabase() {
        final var movies = movieTestDataProvider.generateMovies();
        movies.forEach(movieRepository::save);
    }

    @Test
    @Order(1)
    void givenCinemasInDatabase_whenGetCinemas_thenReturnCinemasList() throws Exception {
        //Given
        List<Cinema> cinemas = cinemaTestDataProvider.generateCinemas();
        cinemas.forEach(cinemaRepository::save);

        //When, Then
        mockMvc.perform(get(CINEMAS_ENDPOINT_PATH))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isNotEmpty(),
                        jsonPath("$.content[0].name").value(cinemas.get(0).getName()),
                        jsonPath("$.content[1].name").value(cinemas.get(1).getName()),
                        jsonPath("$.content[2].name").value(cinemas.get(2).getName()),
                        jsonPath("$.content[0].cinemaManager").value(
                                cinemas.get(0).getCinemaManager().getFirstName() + " " +
                                        cinemas.get(0).getCinemaManager().getLastName()),
                        jsonPath("$.content[1].cinemaManager").value(
                                cinemas.get(1).getCinemaManager().getFirstName() + " " +
                                        cinemas.get(1).getCinemaManager().getLastName()),
                        jsonPath("$.content[2].cinemaManager").value(
                                cinemas.get(2).getCinemaManager().getFirstName() + " " +
                                        cinemas.get(2).getCinemaManager().getLastName()),
                        jsonPath("$.content[0].numberOfScreeningRooms").value(
                                cinemas.get(0).getScreeningRooms().size()),
                        jsonPath("$.content[1].numberOfScreeningRooms").value(
                                cinemas.get(1).getScreeningRooms().size()),
                        jsonPath("$.content[2].numberOfScreeningRooms").value(
                                cinemas.get(2).getScreeningRooms().size()),
                        jsonPath("$.content[0].numberOfAvailableSeats").isNumber(),
                        jsonPath("$.content[1].numberOfAvailableSeats").isNumber(),
                        jsonPath("$.content[2].numberOfAvailableSeats").isNumber(),
                        jsonPath("$.content[0].numberOfUnavailableSeats").isNumber(),
                        jsonPath("$.content[1].numberOfUnavailableSeats").isNumber(),
                        jsonPath("$.content[2].numberOfUnavailableSeats").isNumber()
                );
    }

    @Test
    @Order(2)
    void givenCinemasInDatabase_whenGetCinema_thenReturnCinema() throws Exception {
        // When, Then
        final Cinema cinema = cinemaRepository.findAll().getFirst();
        final var url = CINEMAS_ENDPOINT_PATH + "/" + cinema.getName();
        mockMvc.perform(get(url))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.name").value(cinema.getName()),
                        jsonPath("$.description").value(cinema.getDescription()),
                        jsonPath("$.address.streetAndBuildingNumber").value(
                                cinema.getAddress().getStreetAndBuildingNumber()),
                        jsonPath("$.address.city").value(cinema.getAddress().getCity()),
                        jsonPath("$.address.postalCode").value(cinema.getAddress().getPostalCode()),
                        jsonPath("$.address.country").value(cinema.getAddress().getCountry()),
                        jsonPath("$.screeningRooms").isArray(),
                        jsonPath("$.screeningRooms.length()").value(cinema.getScreeningRooms().size()),
                        jsonPath("$.contactDetails").isArray(),
                        jsonPath("$.contactDetails.length()").value(cinema.getContactDetails().size()),
                        jsonPath("$.cinemaManager.email").value(cinema.getCinemaManager().getEmail())
                );
    }

    @Test
    @Order(3)
    void givenCinemasInDatabase_whenCreateCinema_thenCinemaIsInDatabase() throws Exception {
        // Given
        final var createCinemaRequest = cinemaTestDataProvider.generateCreateCinemaRequest();
        final var image = createImageFile("cinema.jpg", "image data");
        final var cinemaRequest = createCinemaRequestFile(createCinemaRequest);

        // When
        mockMvc.perform(MockMvcRequestBuilders.multipart(CINEMAS_ENDPOINT_PATH)
                        .file(image)
                        .file(cinemaRequest)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated());

        // Then
        Optional<Cinema> optionalCinema = cinemaRepository.findByName(createCinemaRequest.name());
        assertThat(optionalCinema).isPresent();
        Cinema cinema = optionalCinema.get();
        assertAll(
                () -> assertEquals(createCinemaRequest.name(), cinema.getName()),
                () -> assertEquals(createCinemaRequest.description(), cinema.getDescription()),
                () -> assertEquals(createCinemaRequest.address().streetAndBuildingNumber(),
                        cinema.getAddress().getStreetAndBuildingNumber()),
                () -> assertEquals(createCinemaRequest.address().city(), cinema.getAddress().getCity()),
                () -> assertEquals(createCinemaRequest.address().postalCode(), cinema.getAddress().getPostalCode()),
                () -> assertEquals(createCinemaRequest.address().country(), cinema.getAddress().getCountry()),
                () -> assertEquals(createCinemaRequest.screeningRooms().size(), cinema.getScreeningRooms().size()),
                () -> assertEquals(createCinemaRequest.contactDetails().size(), cinema.getContactDetails().size()),
                () -> assertEquals(createCinemaRequest.cinemaManager().email(), cinema.getCinemaManager().getEmail()),
                () -> assertTrue(cinema.getImage().toLowerCase().contains(image.getOriginalFilename().toLowerCase().trim()))
        );
    }

    private MockMultipartFile createImageFile(String filename, String content) {
        return new MockMultipartFile(
                "image", filename, MediaType.IMAGE_JPEG_VALUE,
                content.getBytes()
        );
    }

    private MockMultipartFile createCinemaRequestFile(Object cinemaRequest) throws Exception {
        return new MockMultipartFile(
                "cinemaRequest", "", MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsBytes(cinemaRequest)
        );
    }

    @Test
    @Order(4)
    void givenCinemasInDatabase_whenCreateCinemaWithExistingName_thenBadRequest() throws Exception {
        // Given
        final var createCinemaRequest = cinemaTestDataProvider.generateCreateCinemaRequest();
        final var image = createImageFile("poster.jpg", "image data");
        final var cinemaRequest = createCinemaRequestFile(createCinemaRequest);

        // When
        mockMvc.perform(MockMvcRequestBuilders.multipart(CINEMAS_ENDPOINT_PATH)
                        .file(image)
                        .file(cinemaRequest)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(5)
    void givenCinemasInDatabase_whenUpdateCinema_thenCinemaIsUpdatedInDatabase() throws Exception {
        // Given
        final var cinemaToUpdate = cinemaRepository.findAll().getFirst();
        final var updateCinemaRequest = cinemaTestDataProvider.generateUpdateCinemaRequest(cinemaToUpdate.getName());
        final var image = createImageFile("updated_poster.jpg", "updated image data");
        final var cinemaRequest = createCinemaRequestFile(updateCinemaRequest);

        // When
        final var url = CINEMAS_ENDPOINT_PATH + "/" + cinemaToUpdate.getName();
        mockMvc.perform(MockMvcRequestBuilders.multipart(url)
                        .file(image)
                        .file(cinemaRequest)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(request -> {
                            request.setMethod("PATCH");
                            return request;
                        })
                )
                .andExpect(status().isNoContent());

        // Then
        Optional<Cinema> updatedCinema = cinemaRepository.findByName(updateCinemaRequest.name());
        assertThat(updatedCinema).isPresent();
        Cinema cinema = updatedCinema.get();
        assertAll(
                () -> assertEquals(updateCinemaRequest.name(), cinema.getName()),
                () -> assertEquals(updateCinemaRequest.description(), cinema.getDescription()),
                () -> assertEquals(updateCinemaRequest.address().streetAndBuildingNumber(),
                        cinema.getAddress().getStreetAndBuildingNumber()),
                () -> assertEquals(updateCinemaRequest.address().city(), cinema.getAddress().getCity()),
                () -> assertEquals(updateCinemaRequest.address().postalCode(), cinema.getAddress().getPostalCode()),
                () -> assertEquals(updateCinemaRequest.address().country(), cinema.getAddress().getCountry()),
                () -> assertEquals(updateCinemaRequest.screeningRooms().size(), cinema.getScreeningRooms().size()),
                () -> assertEquals(updateCinemaRequest.contactDetails().size(), cinema.getContactDetails().size()),
                () -> assertEquals(updateCinemaRequest.cinemaManager().email(), cinema.getCinemaManager().getEmail())
        );
    }

    @Test
    @Order(6)
    void givenCinemasInDatabase_whenDeleteCinema_thenCinemaIsNotPresentInDatabase() throws Exception {
        // Given
        final var cinema = cinemaRepository.findAll().getFirst();

        // When
        final var url = CINEMAS_ENDPOINT_PATH + "/" + cinema.getName();
        mockMvc.perform(delete(url))
                .andExpect(status().isNoContent());

        // Then
        assertThat(cinemaRepository.findByName(cinema.getName())).isEmpty();
    }

    @Test
    @Order(7)
    void givenCinemasInDatabase_whenGetCinemasNames_thenReturnCinemasNames() throws Exception {
        // When, Then
        mockMvc.perform(get(CINEMAS_ENDPOINT_PATH + "/names"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isNotEmpty()
                );
    }
}
