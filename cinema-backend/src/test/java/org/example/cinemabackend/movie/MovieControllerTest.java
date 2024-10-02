package org.example.cinemabackend.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemabackend.movie.core.domain.AgeRestriction;
import org.example.cinemabackend.movie.core.domain.Genre;
import org.example.cinemabackend.movie.core.domain.Movie;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.example.cinemabackend.movie.testdata.MovieTestDataProvider;
import org.example.cinemabackend.user.core.domain.Role;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.example.cinemabackend.user.infrastructure.adapter.secondary.UserJpaRepository;
import org.example.cinemabackend.user.infrastructure.schema.UserSchema;
import org.example.cinemabackend.user.testdata.UserTestDataProvider;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieControllerTest {

    private static final String MOVIES_ENDPOINT_PATH = "/v1/movies";

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MovieTestDataProvider movieTestDataProvider;

    @Autowired
    private UserTestDataProvider userTestDataProvider;

    @BeforeAll
    void setUp() {
        saveCinemaNetworkManagerToDb();
    }

    private void saveCinemaNetworkManagerToDb() {
        final var cinemaNetworkManager = userTestDataProvider.generateSampleCinemaNetworkManager();
        userRepository.save(cinemaNetworkManager);
    }


    @Test
    @Order(1)
    void givenNoMoviesInDatabase_whenGetMoviesPage_thenStatusIsOkAndEmptyPageIsReturned() throws Exception {
        //Given
        loginAsCinemaNetworkManager();

        //When, Then
        mockMvc.perform(get(MOVIES_ENDPOINT_PATH))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isEmpty(),
                        jsonPath("$.content").isArray()
                );
    }

    private void loginAsCinemaNetworkManager() {
        final var cinemaManager = userJpaRepository.findAll();
        cinemaManager.stream()
                .filter(user -> user.getRole().equals(Role.CINEMA_NETWORK_MANAGER))
                .findFirst()
                .ifPresent(this::login);
    }

    private void login(UserSchema userSchema) {
        final var username = userSchema.getUsername();
        final var authorities = userSchema.getAuthorities();
        final var authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    @Order(2)
    void givenMoviesInDatabase_whenGetMoviesPage_thenStatusIsOkAndPageWithMoviesIsReturned() throws Exception {
        //Given
        loginAsCinemaNetworkManager();
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
    public void givenMoviesInDatabase_whenGetMovie_thenStatusIsOkAndMovieIsReturned() throws Exception {
        // Given
        loginAsCinemaNetworkManager();
        final var movie = movieRepository.findAll().stream().findFirst().orElseThrow();

        // When, Then
        mockMvc.perform(get(MOVIES_ENDPOINT_PATH + "/" + movie.getTitle()))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.title").value(movie.getTitle()),
                        jsonPath("$.originalTitle").value(movie.getOriginalTitle()),
                        jsonPath("$.duration").value(movie.getDuration()),
                        jsonPath("$.releaseDate").value(movie.getReleaseDate().toString()),
                        jsonPath("$.description").value(movie.getDescription()),
                        jsonPath("$.poster").isString(),
                        jsonPath("$.ageRestriction").value(String.valueOf(movie.getAgeRestriction())),
                        jsonPath("$.productionDetails.worldPremiereDate")
                                .value(movie.getProductionDetails().getWorldPremiereDate().toString()),
                        jsonPath("$.productionDetails.actors").isArray(),
                        jsonPath("$.productionDetails.actors.length()").value(movie.getProductionDetails().getActors().size()),
                        jsonPath("$.productionDetails.originalLanguages").isArray(),
                        jsonPath("$.productionDetails.originalLanguages.length()")
                                .value(movie.getProductionDetails().getOriginalLanguages().size()),
                        jsonPath("$.productionDetails.productionCountries").isArray(),
                        jsonPath("$.productionDetails.productionCountries.length()")
                                .value(movie.getProductionDetails().getProductionCountries().size()),
                        jsonPath("$.productionDetails.director.firstName")
                                .value(movie.getProductionDetails().getDirector().getFirstName()),
                        jsonPath("$.productionDetails.director.lastName")
                                .value(movie.getProductionDetails().getDirector().getLastName()),
                        jsonPath("$.trailer").value(movie.getTrailer()),
                        jsonPath("$.genres").isArray(),
                        jsonPath("$.genres.length()").value(movie.getGenres().size()),
                        jsonPath("$.movieVariants").isArray(),
                        jsonPath("$.movieVariants.length()").value(movie.getMovieVariants().size())
                );
    }

    @Test
    @Order(4)
    public void givenMoviesInDatabase_whenCreateMovie_thenStatusIsCreatedAndMovieIsInDatabase() throws Exception {
        // Given
        loginAsCinemaNetworkManager();

        final var createMovieRequest = movieTestDataProvider.generateCreateMovieRequest();
        MockMultipartFile image = new MockMultipartFile(
                "image", "poster-test.jpg", MediaType.IMAGE_JPEG_VALUE, "image data".getBytes());
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
                () -> assertEquals(createMovieRequest.trailer(), movie.getTrailer()),
                () -> assertTrue(movie.getPoster().toLowerCase().contains(image.getOriginalFilename().toLowerCase().trim())),
                () -> assertEquals(createMovieRequest.genres().size(), movie.getGenres().size()),
                () -> assertEquals(createMovieRequest.movieVariants().size(), movie.getMovieVariants().size()),
                () -> assertEquals(createMovieRequest.movieVariants().stream().findFirst().get().language(),
                        movie.getMovieVariants().stream().findFirst().get().getLanguage())
        );
    }

    @Test
    @Order(5)
    public void givenMovieInDatabase_whenDeleteMovie_thenStatusIsNoContentAndMovieIsDeleted() throws Exception {
        // Given
        loginAsCinemaNetworkManager();
        Movie movieToDelete = movieRepository.findAll().stream().findFirst().orElseThrow();
        final int movieRepositorySize = movieRepository.findAll().size();

        // When
        final var url = MOVIES_ENDPOINT_PATH + "/" + movieToDelete.getTitle();
        mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andExpect(status().isNoContent());

        // Then
        assertThat(movieRepository.findByTitle(movieToDelete.getTitle())).isEmpty();
        assertEquals(movieRepositorySize - 1, movieRepository.findAll().size());
    }

    @Test
    @Order(6)
    public void givenMoviesInDatabase_WhenGetMovieTitles_ThenStatusIsOkAndMovieTitlesAreReturned() throws Exception {
        //Given
        loginAsCinemaNetworkManager();

        // When, Then
        mockMvc.perform(get(MOVIES_ENDPOINT_PATH + "/titles"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isNotEmpty(),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(movieRepository.findAll().size()),
                        jsonPath("$.content[0]").isString()
                );
    }

    @Test
    @Order(7)
    public void givenMoviesInDatabase_WhenGetAgeRestrictions_ThenStatusIsOkAndMovieTitlesAreReturned() throws Exception {
        //Given
        loginAsCinemaNetworkManager();

        // When, Then
        mockMvc.perform(get(MOVIES_ENDPOINT_PATH + "/age-restrictions"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isNotEmpty(),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(AgeRestriction.values().length)
                );
    }

    @Test
    @Order(7)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void givenMoviesInDatabase_WhenGetGenres_ThenStatusIsOkAndMovieTitlesAreReturned() throws Exception {
        //Given
        saveCinemaNetworkManagerToDb();
        loginAsCinemaNetworkManager();

        // When, Then
        mockMvc.perform(get(MOVIES_ENDPOINT_PATH + "/genres"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isNotEmpty(),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(Genre.values().length)
                );
    }

    @Test
    @Order(8)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void givenNoMoviesInDatabase_whenCreateMovieWithTheSameTitleTwice_thenBadRequestIsReturned() throws Exception {
        // Given
        saveCinemaNetworkManagerToDb();
        loginAsCinemaNetworkManager();

        movieTestDataProvider.generateMovies().forEach(movieRepository::save);
        final var createMovieRequest = movieTestDataProvider.generateCreateMovieRequest();
        MockMultipartFile image = new MockMultipartFile(
                "image", "poster-test.jpg", MediaType.IMAGE_JPEG_VALUE, "image data".getBytes());
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
        mockMvc.perform(MockMvcRequestBuilders.multipart(MOVIES_ENDPOINT_PATH)
                        .file(image)
                        .file(movieRequest)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(9)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void givenMoviesInDatabase_whenUpdateMovie_thenStatusIsNoContentAndMovieIsUpdated() throws Exception {
        // Given
        saveCinemaNetworkManagerToDb();
        loginAsCinemaNetworkManager();

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

    @Test
    @Order(10)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void whenMoviesInDatabase_whenDeleteSameMovieTwice_thenStatusIsNotFound() throws Exception {
        // Given
        saveCinemaNetworkManagerToDb();
        loginAsCinemaNetworkManager();

        final var movies = movieTestDataProvider.generateMovies();
        movies.forEach(movieRepository::save);

        final var movie = movieRepository.findAll().stream().findFirst().orElseThrow();

        // When
        final var url = MOVIES_ENDPOINT_PATH + "/" + movie.getTitle();
        mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andExpect(status().isNoContent());

        // Then
        mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andExpect(status().isNotFound());

        assertThat(movieRepository.findByTitle(movie.getTitle())).isEmpty();
    }
}
