package org.example.cinemabackend.auth.core.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.cinemabackend.auth.application.dto.JwtDto;
import org.example.cinemabackend.auth.application.dto.request.LoginUserRequest;
import org.example.cinemabackend.auth.application.dto.request.RegisterUserRequest;
import org.example.cinemabackend.auth.application.dto.request.ResetPasswordRequest;
import org.example.cinemabackend.auth.core.port.primary.*;
import org.example.cinemabackend.user.application.dto.request.UpdatePasswordRequest;
import org.example.cinemabackend.user.core.domain.Customer;
import org.example.cinemabackend.user.core.domain.Role;
import org.example.cinemabackend.user.core.port.primary.UserUseCases;
import org.example.cinemabackend.user.core.port.secondary.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
class CustomerAuthService implements CustomerAuthUseCases {
    private static final String PASSWORD_DOES_NOT_MATCH_ERROR_MESSAGE = "Invalid login credentials";
    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "Invalid login credentials";
    private static final String FRONTEND_BASE_URL = "http://localhost:4200";
    private static final String ACCOUNT_VERIFICATION_URL_PREFIX = FRONTEND_BASE_URL + "/registration/verify-user?token=";
    private static final String RESET_PASSWORD_URL_PREFIX = FRONTEND_BASE_URL + "/reset-password-form?token=";
    private final Logger LOGGER = LogManager.getLogger(CustomerAuthService.class);
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final EmailUseCases emailUseCases;
    private final TokenUseCases tokenUseCases;
    private final JwtUseCases jwtUseCases;
    private final UserSecurityContextUseCases userSecurityContextUseCases;
    private final UserUseCases userUseCases;

    @Override
    public JwtDto login(LoginUserRequest loginUserDto) {
        final var customer = validateCustomerExistence(loginUserDto.email());
        checkPasswordsMatch(loginUserDto.password(), customer.getPassword());
        checkIfCustomerAccountIsVerified(customer);
        final var jwt = jwtUseCases.createAndEncodeJwt(customer);
        return new JwtDto(jwt);
    }

    @Override
    public void register(RegisterUserRequest registerUserRequest) {
        userUseCases.checkIfUserExists(registerUserRequest.email());
        checkUserRoleIsCustomer(registerUserRequest.role());
        final var encodedPassword = passwordEncoder.encode(registerUserRequest.password());
        final var customer = createNewCustomer(registerUserRequest, encodedPassword);
        customerRepository.save(customer);
        LOGGER.info("User " + customer.getEmail() + " with role " + customer.getRole() + " created");
        sendVerificationEmail(customer);
    }

    private void sendVerificationEmail(Customer customer) {
        final String verificationUrl = ACCOUNT_VERIFICATION_URL_PREFIX + tokenUseCases.generateToken(customer);
        LOGGER.info("Sending email to: " + customer.getEmail() + " with verification url: " + verificationUrl);
        emailUseCases.sendEmailToConfirmAccount(customer.getEmail(), verificationUrl);
    }

    private Customer createNewCustomer(RegisterUserRequest registerUserRequest, String encodedPassword) {
        return new Customer(
                registerUserRequest.firstName(),
                registerUserRequest.lastName(),
                registerUserRequest.email(),
                encodedPassword,
                registerUserRequest.role(),
                false,
                LocalDateTime.now()
        );
    }

    @Override
    public void processRequestForPasswordReset(String email) {
        final var user = validateCustomerExistence(email);
        final String resetPasswordUrl = RESET_PASSWORD_URL_PREFIX + tokenUseCases.generateToken(user);
        emailUseCases.sendEmailToResetPassword(user.getEmail(), resetPasswordUrl);
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        tokenUseCases.validateToken(resetPasswordRequest.token());
        String email = tokenUseCases.getEmailFromToken(resetPasswordRequest.token());
        final var user = validateCustomerExistence(email);
        final var encodedPassword = passwordEncoder.encode(resetPasswordRequest.newPassword());
        user.setPassword(encodedPassword);
        customerRepository.save(user);
    }

    @Override
    public void verifyCustomerAccount(String email) {
        final var customer = customerRepository.findByEmail(email).orElseThrow();
        customer.setIsAccountVerified(true);
        customerRepository.save(customer);
    }

    @Override
    public void updatePassword(UpdatePasswordRequest updateCustomerProfileRequest) {
        final var email = userSecurityContextUseCases.getCurrentUserEmail();
        final var customer = customerRepository.findByEmail(email).orElseThrow();
        validateCurrentPasswordIsCorrect(customer, updateCustomerProfileRequest.currentPassword());
        customer.setPassword(passwordEncoder.encode(updateCustomerProfileRequest.newPassword()));
        customerRepository.save(customer);
    }

    private void validateCurrentPasswordIsCorrect(Customer customer, String currentPassword) {
        if (!passwordEncoder.matches(currentPassword, customer.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }
    }

    private void checkUserRoleIsCustomer(Role role) {
        if (!role.equals(Role.CUSTOMER)) {
            throw new IllegalStateException("Only customers can register");
        }
    }

    private void checkIfCustomerAccountIsVerified(Customer customer) {
        if (!customer.isAccountVerified()) {
            throw new IllegalStateException("Account is not verified");
        }
    }

    private Customer validateCustomerExistence(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND_ERROR_MESSAGE));
    }

    private void checkPasswordsMatch(String password, String encodedPassword) {
        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new IllegalStateException(PASSWORD_DOES_NOT_MATCH_ERROR_MESSAGE);
        }
    }
}
