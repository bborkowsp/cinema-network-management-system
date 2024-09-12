package org.example.cinemabackend.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemabackend.movie.core.domain.Movie;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(roles = "CINEMA_NETWORK_MANAGER")
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
    void givenMoviesInDatabase_whenGetMoviesPage_thenStatusIsOkAndPageWithMoviesIsReturned() throws Exception {
        //Given
        final var movies = movieTestDataProvider.generateMovies();
        movies.forEach(movieRepository::save);

        final int pageNumber = 0;
        final int pageSize = 3;

        //When, Then
        mockMvc.perform(get(MOVIES_ENDPOINT_PATH)
                        .param("page", String.valueOf(pageNumber))
                        .param("size", String.valueOf(pageSize)))
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
                        jsonPath("$.content[1].title").value(movies.get(1).getTitle()),
                        jsonPath("$.content[2].title").value(movies.get(2).getTitle())
                );
    }

    @Test
    @Order(3)
    public void givenMoviesInDatabase_whenCreateMovie_thenStatusIsCreatedAndMovieIsInDatabase() throws Exception {
        // Given
        final var createMovieRequest = movieTestDataProvider.generateCreateMovieRequest();
        MockMultipartFile image = new MockMultipartFile(
                "image", "poster.jpg", MediaType.IMAGE_JPEG_VALUE, "image data".getBytes());
        MockMultipartFile movieRequest = new MockMultipartFile(
                "movieRequest", "", MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsBytes(createMovieRequest)
        );

        // When
        mockMvc.perform(MockMvcRequestBuilders.multipart(MOVIES_ENDPOINT_PATH)
                        .file(image)
                        .file(movieRequest)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated());

        // Then
        Optional<Movie> optionalMovie = movieRepository.findByTitle(createMovieRequest.title());
        assertThat(optionalMovie).isPresent();
        Movie movie = optionalMovie.get();
        assertAll(
                () -> assertNotNull(movie.getId()),
                () -> assertEquals(createMovieRequest.title(), movie.getTitle()),
                () -> assertEquals(createMovieRequest.originalTitle(), movie.getOriginalTitle()),
                () -> assertEquals(createMovieRequest.duration(), movie.getDuration()),
                () -> assertEquals(createMovieRequest.releaseDate(), movie.getReleaseDate()),
                () -> assertEquals(createMovieRequest.description(), movie.getDescription()),
                () -> assertEquals(createMovieRequest.ageRestriction(), movie.getAgeRestriction()),
                () -> assertEquals(createMovieRequest.productionDetails().worldPremiereDate(),
                        movie.getProductionDetails().getWorldPremiereDate()),
                () -> assertEquals(createMovieRequest.productionDetails().actors().size(),
                        movie.getProductionDetails().getActors().size()),
                () -> assertEquals(createMovieRequest.productionDetails().originalLanguages().size(),
                        movie.getProductionDetails().getOriginalLanguages().size()),
                () -> assertEquals(createMovieRequest.productionDetails().productionCountries().size(),
                        movie.getProductionDetails().getProductionCountries().size()),
                () -> assertEquals(createMovieRequest.productionDetails().director().firstName(),
                        movie.getProductionDetails().getDirector().getFirstName()),
                () -> assertEquals(createMovieRequest.productionDetails().director().lastName(),
                        movie.getProductionDetails().getDirector().getLastName()),
                () -> assertEquals(createMovieRequest.subtitleAndSoundOptions().subtitles(),
                        movie.getSubtitleAndSoundOptions().isSubtitles()),
                () -> assertEquals(createMovieRequest.subtitleAndSoundOptions().dubbing(),
                        movie.getSubtitleAndSoundOptions().isDubbing()),
                () -> assertEquals(createMovieRequest.subtitleAndSoundOptions().voiceOver(),
                        movie.getSubtitleAndSoundOptions().isVoiceOver()),
                () -> assertEquals(createMovieRequest.subtitleAndSoundOptions().originalLanguage(),
                        movie.getSubtitleAndSoundOptions().isOriginalLanguage()),
                () -> assertEquals(createMovieRequest.trailer(), movie.getTrailer()),
                () -> assertTrue(movie.getPoster().toLowerCase().contains(image.getOriginalFilename().toLowerCase().trim())),
                () -> assertEquals(createMovieRequest.genres().size(), movie.getGenres().size()),
                () -> assertEquals(createMovieRequest.projectionTechnologies().size(),
                        movie.getProjectionTechnologies().size())
        );
    }

    @Test
    @Order(4)
    @DirtiesContext
    public void givenMoviesInDatabase_whenUpdateMovie_thenStatusIsNoContentAndMovieIsUpdated() throws Exception {
        // Given
        final var movies = movieTestDataProvider.generateMovies();
        movies.forEach(movieRepository::save);

        final var movieToUpdate = movies.getFirst();
        final var updateMovieRequest = movieTestDataProvider.generateUpdateMovieRequest();
        MockMultipartFile image = new MockMultipartFile(
                "image", "updated_poster.jpg", MediaType.IMAGE_JPEG_VALUE,
                "updated image data".getBytes()
        );
        MockMultipartFile movieRequest = new MockMultipartFile(
                "movieRequest", "", MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsBytes(updateMovieRequest)
        );

        // When
        final var url = MOVIES_ENDPOINT_PATH + "/" + movieToUpdate.getTitle();
        mockMvc.perform(MockMvcRequestBuilders.multipart(url)
                        .file(image)
                        .file(movieRequest)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(request -> {
                            request.setMethod("PATCH");
                            return request;
                        })
                )
                .andExpect(status().isNoContent());

        //Then
        Optional<Movie> optionalMovie = movieRepository.findByTitle(updateMovieRequest.title());
        assertThat(optionalMovie).isPresent();
        Movie updatedMovie = optionalMovie.get();
        assertAll(
                () -> assertEquals(updateMovieRequest.title(), updatedMovie.getTitle()),
                () -> assertEquals(updateMovieRequest.originalTitle(), updatedMovie.getOriginalTitle()),
                () -> assertEquals(updateMovieRequest.duration(), updatedMovie.getDuration()),
                () -> assertEquals(updateMovieRequest.releaseDate(), updatedMovie.getReleaseDate()),
                () -> assertEquals(updateMovieRequest.description(), updatedMovie.getDescription()),
                () -> assertEquals(updateMovieRequest.ageRestriction(), updatedMovie.getAgeRestriction())
        );
    }
}
