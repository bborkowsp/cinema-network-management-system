package org.example.cinemabackend.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.example.cinemabackend.movie.testdata.MovieTestDataProvider;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@WithMockUser
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovieControllerTest {

    private static final String MOVIES_ENDPOINT_PATH = "/v1/movies";

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MovieTestDataProvider movieTestDataProvider;

    @Test
    @Order(1)
    void givenNoMoviesInDatabase_whenGetMoviesPage_thenStatusIsOkAndEmptyPageIsReturned() throws Exception {
        //When, Then
        mockMvc.perform(get(MOVIES_ENDPOINT_PATH))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isEmpty(),
                        jsonPath("$.content").isArray()
                );
    }

    @Test
    @Order(2)
    @DirtiesContext
    void givenMoviesInDatabase_whenGetMoviesPage_thenStatusIsOkAndPageWithMoviesIsReturned() throws Exception {
        //Given
        final var movies = movieTestDataProvider.generateMovies();
        movies.forEach(movieRepository::save);

        final var pageNumber = "0";
        final var pageSize = "3";

        //When, Then
        mockMvc.perform(get(MOVIES_ENDPOINT_PATH)
                        .param("page", pageNumber)
                        .param("size", pageSize))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isNotEmpty(),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(pageSize),
                        jsonPath("$.content[0].title").value(movies.get(0).getTitle()),
                        jsonPath("$.content[0].originalTitle").value(movies.get(0).getOriginalTitle()),
                        jsonPath("$.content[0].duration").value(movies.get(0).getDuration()),
                        jsonPath("$.content[0].director.firstName")
                                .value(movies.get(0).getProductionDetails().getDirector().getFirstName()),
                        jsonPath("$.content[0].image.data").exists(),
                        jsonPath("$.content[1].title").value(movies.get(1).getTitle()),
                        jsonPath("$.content[2].title").value(movies.get(2).getTitle())
                );
    }

    
}
