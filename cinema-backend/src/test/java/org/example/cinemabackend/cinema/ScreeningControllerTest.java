package org.example.cinemabackend.cinema;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(roles = "CINEMA_MANAGER")
public class ScreeningControllerTest {
    private static final String SCREENINGS_ENDPOINT_PATH = "/v1/screenings";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ScreeningRepository screeningRepository;


    @Test
    @Order(1)
    void givenNoScreeningsInDb_whenGetScreenings_thenStatusIsOkAndEmptyListIsReturned() throws Exception {

    }

    @Test
    @Order(2)
    void givenScreeningsInDb_whenGetScreenings_thenStatusIsOkAndScreeningsAreReturned() throws Exception {
        //Given

    }
}
