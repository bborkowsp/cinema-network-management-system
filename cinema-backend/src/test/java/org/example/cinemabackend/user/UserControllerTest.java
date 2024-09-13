package org.example.cinemabackend.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemabackend.user.application.dto.request.CreateUserRequest;
import org.example.cinemabackend.user.application.dto.request.UpdateUserRequest;
import org.example.cinemabackend.user.core.domain.Role;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(roles = "ADMIN")
public class UserControllerTest {
    private static final String USERS_ENDPOINT_URL = "/v1/users";
    private static final String CINEMA_NETWORK_MANAGERS_ENDPOINT_PREFIX = USERS_ENDPOINT_URL + "/cinema-network-managers";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "user@example.com";
    private static final String PASSWORD = "password";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;


    @Test
    @Order(1)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void givenNoUsers_whenGetUsers_thenReturnEmptyPage() throws Exception {
        //When, Then
        mockMvc.perform(get(USERS_ENDPOINT_URL))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isEmpty(),
                        jsonPath("$.content").isArray()
                );
    }

    @Test
    @Order(2)
    void givenNoUsers_whenGetUser_thenReturnNotFound() throws Exception {
        //When, Then
        final var url = USERS_ENDPOINT_URL + "/email";
        mockMvc.perform(get(url))
                .andExpect(status().isNotFound());
    }

    @Order(3)
    @ParameterizedTest
    @EnumSource(value = Role.class, names = {"CINEMA_MANAGER", "CINEMA_NETWORK_MANAGER", "ADMIN", "CUSTOMER"})
    void givenNoUsers_whenCreateUser_thenReturnCreated(Role role) throws Exception {
        //Given
        final var email = EMAIL + role;
        final var createUserRequest = new CreateUserRequest(FIRST_NAME, LAST_NAME, email, PASSWORD, role);

        //When
        mockMvc.perform(post(CINEMA_NETWORK_MANAGERS_ENDPOINT_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserRequest)))
                .andExpect(status().isCreated());

        //Then
        Optional<User> optionalUser = userRepository.findByEmail(email);
        assertThat(optionalUser).isPresent();
        User user = optionalUser.get();
        assertAll(
                () -> assertEquals(createUserRequest.firstName(), user.getFirstName()),
                () -> assertEquals(createUserRequest.lastName(), user.getLastName()),
                () -> assertEquals(createUserRequest.email(), user.getEmail())
        );
    }

    @Order(4)
    @ParameterizedTest
    @EnumSource(value = Role.class, names = {"CINEMA_MANAGER", "CINEMA_NETWORK_MANAGER", "ADMIN", "CUSTOMER"})
    void givenUsersInDb_whenCreateUserWithSameEmail_thenReturnBadRequest(Role role) throws Exception {
        //Given
        final var email = EMAIL + role;
        final var createUserRequest = new CreateUserRequest(FIRST_NAME, LAST_NAME, email, PASSWORD, role);

        //When, Then
        mockMvc.perform(post(CINEMA_NETWORK_MANAGERS_ENDPOINT_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserRequest)))
                .andExpect(status().isBadRequest());
    }

    @Order(5)
    @Test
    void givenUsersInDb_whenGetUsers_thenPageWithUsersIsReturned() throws Exception {
        //When, Then
        mockMvc.perform(get(USERS_ENDPOINT_URL))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isNotEmpty(),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content[0].email").value(EMAIL + Role.CINEMA_MANAGER),
                        jsonPath("$.content[0].firstName").value(FIRST_NAME),
                        jsonPath("$.content[3].email").value(EMAIL + Role.ADMIN),
                        jsonPath("$.content[3].firstName").value(FIRST_NAME)
                );
    }

    @Order(6)
    @Test
    void givenUsersInDb_whenGetUser_thenUserIsReturned() throws Exception {
        //Given
        final var email = EMAIL + Role.CINEMA_MANAGER;

        //When, Then
        final var url = USERS_ENDPOINT_URL + "/" + email;
        mockMvc.perform(get(url))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.email").value(email),
                        jsonPath("$.role").value(Role.CINEMA_MANAGER.toString()),
                        jsonPath("$.firstName").value(FIRST_NAME),
                        jsonPath("$.lastName").value(LAST_NAME)
                );
    }

    @Order(7)
    @Test
    void givenUsersInDb_whenUpdateUser_thenUserIsUpdated() throws Exception {
        //Given
        final var email = EMAIL + Role.CINEMA_MANAGER;
        final var updateEmail = "new" + email;
        final var updateFirstName = "Jane";
        final var updateLastName = "Eod";
        final var updatePassword = "new" + PASSWORD;
        final var updateUserRequest = new UpdateUserRequest(updateFirstName, updateLastName, updateEmail, PASSWORD, updatePassword);

        //When
        final var url = CINEMA_NETWORK_MANAGERS_ENDPOINT_PREFIX + "/" + email;
        mockMvc.perform(patch(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateUserRequest)))
                .andExpect(status().isNoContent());

        //Then
        Optional<User> optionalUser = userRepository.findByEmail(updateEmail);
        assertThat(optionalUser).isPresent();
        User user = optionalUser.get();
        assertAll(
                () -> assertEquals(updateUserRequest.firstName(), user.getFirstName()),
                () -> assertEquals(updateUserRequest.lastName(), user.getLastName()),
                () -> assertEquals(updateUserRequest.email(), user.getEmail())
        );
    }


    @Order(8)
    @Test
    void givenUsersInDb_whenDeleteUser_thenUserIsDeleted() throws Exception {
        //Given
        final var email = EMAIL + Role.CINEMA_NETWORK_MANAGER;

        //When
        final var url = USERS_ENDPOINT_URL + "/" + email;
        mockMvc.perform(delete(url))
                .andExpect(status().isNoContent());

        mockMvc.perform(get(url))
                .andExpect(status().isNotFound());
    }
}
