package org.example.cinemabackend.cinema;

import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.testdata.CinemaTestDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@WithMockUser
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CinemaControllerTest {

    private static final String CINEMA_ENDPOINT_PATH = "/v1/cinemas";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CinemaTestDataProvider cinemaTestDataProvider;

    @Test
    void givenCinemasInDatabase_whenGetCinemas_thenReturnCinemasList() throws Exception {
        //Given
        List<Cinema> cinemas = cinemaTestDataProvider.generateSampleCinemas();
        cinemas.forEach(cinemaRepository::save);

        //When, Then
        mockMvc.perform(get(CINEMA_ENDPOINT_PATH))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isNotEmpty(),
                        jsonPath("$.content[0].name").value(cinemas.get(0).getName()),
                        jsonPath("$.content[1].name").value(cinemas.get(1).getName()),
                        jsonPath("$.content[2].name").value(cinemas.get(2).getName())
                );
    }
}
