package org.example.cinemabackend.user.core.service;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.cinemabackend.config.JwtConfig;
import org.example.cinemabackend.user.application.dto.JwtDto;
import org.example.cinemabackend.user.application.dto.request.LoginUserRequest;
import org.example.cinemabackend.user.application.dto.request.RegisterUserRequest;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.primary.AccountVerificationUseCases;
import org.example.cinemabackend.user.core.port.primary.AuthUseCases;
import org.example.cinemabackend.user.core.port.primary.EmailUseCases;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
class AuthService implements AuthUseCases, UserDetailsService {
    private static final Logger LOGGER = LogManager.getLogger(AuthService.class);
    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "User not found";
    private static final String PASSWORD_DOES_NOT_MATCH_ERROR_MESSAGE = "Invalid login credentials";
    private static final String USER_ALREADY_EXISTS_ERROR_MESSAGE = "User already exists";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;
    private final EmailUseCases emailUseCases;
    private final AccountVerificationUseCases accountVerificationUseCases;

    @Override
    public JwtDto login(LoginUserRequest loginUserDto) {
        final var user = validateUserExistence(loginUserDto.email());
        checkPasswordsMatch(loginUserDto.password(), user.getPassword());
        final var jwt = createAndEncodeJwt(user);
        return new JwtDto(jwt);
    }

    @Override
    public void register(RegisterUserRequest registerUserRequest) {
        checkIfUserAlreadyExists(registerUserRequest.email());
        final var encodedPassword = passwordEncoder.encode(registerUserRequest.password());
        final var user = new User(
                registerUserRequest.firstName(),
                registerUserRequest.lastName(),
                registerUserRequest.email(),
                encodedPassword,
                registerUserRequest.role()
        );
        userRepository.save(user);
        final String verificationUrl = "http://localhost:4200/registration/verify-user?token=" + accountVerificationUseCases.generateAccountVerificationToken(user);
        LOGGER.info("Sending email to: " + user.getEmail() + " with verification url: " + verificationUrl);
        emailUseCases.sendEmailToConfirmAccount(user.getEmail(), "Email Verification", verificationUrl);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_ERROR_MESSAGE));
    }

    private void checkPasswordsMatch(String password, String encodedPassword) {
        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new IllegalStateException(PASSWORD_DOES_NOT_MATCH_ERROR_MESSAGE);
        }
    }

    private String createAndEncodeJwt(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("authorities", user.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(jwtConfig.getSecretKey())
                .compact();
    }

    private User validateUserExistence(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND_ERROR_MESSAGE));
    }

    private void checkIfUserAlreadyExists(String username) {
        if (userRepository.existsByEmail(username)) {
            throw new IllegalStateException(USER_ALREADY_EXISTS_ERROR_MESSAGE);
        }
    }
}
