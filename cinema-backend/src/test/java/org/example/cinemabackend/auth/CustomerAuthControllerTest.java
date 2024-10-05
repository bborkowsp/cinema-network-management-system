package org.example.cinemabackend.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemabackend.auth.application.dto.request.LoginUserRequest;
import org.example.cinemabackend.auth.application.dto.request.RegisterUserRequest;
import org.example.cinemabackend.user.core.domain.Role;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerAuthControllerTest {
    private static final String CUSTOMER_AUTH_ENDPOINT_URL = "/v1/auth-customer";
    private static final String REGISTER_ENDPOINT_URL = CUSTOMER_AUTH_ENDPOINT_URL + "/register";
    private static final String LOGIN_ENDPOINT_URL = CUSTOMER_AUTH_ENDPOINT_URL + "/login";
    private static final String RESET_PASSWORD_REQUEST_ENDPOINT_URL = CUSTOMER_AUTH_ENDPOINT_URL + "/reset-password-request";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "user@example.com";
    private static final String PASSWORD = "password";
    private static final Role ROLE = Role.CUSTOMER;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @Order(1)
    void givenValidRegisterCustomerUserRequest_whenRegister_thenReturnCreated() throws Exception {
        //Given
        final var registerUserRequest = new RegisterUserRequest(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE);

        //When, Then
        mockMvc.perform(post(REGISTER_ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerUserRequest)))
                .andExpect(status().isCreated());
    }

    @Order(2)
    @ParameterizedTest
    @EnumSource(value = Role.class, names = {"CINEMA_MANAGER", "CINEMA_NETWORK_MANAGER", "ADMIN"})
    void givenNonCustomerRole_whenRegister_thenReturnBadRequest(Role role) throws Exception {
        //Given
        final var registerUserRequest = new RegisterUserRequest(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, role);

        //When, Then
        mockMvc.perform(post(REGISTER_ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerUserRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    void givenUserCustomerAlreadyRegistered_whenRegister_thenReturnBadRequest() throws Exception {
        //Given
        final var registerUserRequest = new RegisterUserRequest(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE);

        //When, Then
        mockMvc.perform(post(REGISTER_ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerUserRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(4)
    void givenValidLoginUserRequestAndCustomerNotVerified_whenLogin_thenReturnBadRequest() throws Exception {
        //Given
        final var loginUserRequest = new LoginUserRequest(EMAIL, PASSWORD);

        //When, Then
        mockMvc.perform(post(LOGIN_ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUserRequest)))
                .andExpect(status().isBadRequest());
    }

    @Order(5)
    @ParameterizedTest
    @ValueSource(strings = {"password1", "2password", "&#150;"})
    void givenInvalidLoginUserRequest_whenLogin_thenReturnBadRequest(String invalidPassword) throws Exception {
        //Given
        final var loginUserRequest = new LoginUserRequest(EMAIL, invalidPassword);

        //When, Then
        mockMvc.perform(post(LOGIN_ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUserRequest)))
                .andExpect(status().isBadRequest());
    }

    @Order(6)
    @ParameterizedTest
    @ValueSource(strings = {"usernotfound@example.com", "nonexistent@domain.com"})
    void givenEmailNotInDb_whenResetPassword_thenReturnIsNotFound(String emailNotInDb) throws Exception {
        // When, Then
        final var url = RESET_PASSWORD_REQUEST_ENDPOINT_URL + "?email=" + emailNotInDb;
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}