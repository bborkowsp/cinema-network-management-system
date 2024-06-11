package org.example.cinemabackend.cinema;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.testdata.CinemaTestDataProvider;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(authorities = "CINEMA_NETWORK_MANAGER")
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


    @Test
    @Order(1)
    void givenCinemasInDatabase_whenGetCinemas_thenReturnCinemasList() throws Exception {
        //Given
        List<Cinema> cinemas = cinemaTestDataProvider.generateSampleCinemas();
        cinemas.forEach(cinemaRepository::save);

        //When, Then
        mockMvc.perform(get(CINEMAS_ENDPOINT_PATH))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isNotEmpty(),
                        jsonPath("$.content[0].name").value(cinemas.get(0).getName()),
                        jsonPath("$.content[1].name").value(cinemas.get(1).getName()),
                        jsonPath("$.content[2].name").value(cinemas.get(2).getName())
                );
    }

    @Test
    @Order(2)
    void givenCinemasInDatabase_whenGetCinema_thenReturnCinema() throws Exception {
        // Given
        // Cinemas are in database thanks to previous test

        // When, Then
        final Cinema cinema = cinemaRepository.findAll().getFirst();
        final var url = CINEMAS_ENDPOINT_PATH + "/" + cinema.getName();
        mockMvc.perform(get(url))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.name").value(cinema.getName())
                );
    }

    @Test
    @Order(3)
    void givenCinemasInDatabase_whenCreateCinema_thenCinemaIsInDatabase() throws Exception {
        // Given
        final var createCinemaRequest = cinemaTestDataProvider.generateCreateCinemaRequest();

        // When
        mockMvc.perform(post(CINEMAS_ENDPOINT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCinemaRequest)))
                .andExpect(status().isCreated());

        // Then
        assertThat(cinemaRepository.findByName(createCinemaRequest.name())).isPresent();
    }

    @Test
    @Order(4)
    void givenCinemasInDatabase_whenUpdateCinema_thenCinemaIsUpdatedInDatabase() throws Exception {
        // Given
        final var cinema = cinemaRepository.findAll().getFirst();
        final var updateCinemaRequest = cinemaTestDataProvider.generateUpdateCinemaRequest(cinema.getName());

        // When
        final var url = CINEMAS_ENDPOINT_PATH + "/" + cinema.getName();
        mockMvc.perform(patch(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateCinemaRequest)))
                .andExpect(status().isNoContent());

        // Then
        final var updatedCinema = cinemaRepository.findByName(updateCinemaRequest.name());
        assertAll(
                () -> assertThat(updatedCinema).isPresent(),
                () -> assertEquals(updateCinemaRequest.name(), updatedCinema.get().getName())
        );
    }

    @Test
    @Order(5)
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
}
