package org.example.cinemabackend.user.core.service;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.config.JwtConfig;
import org.example.cinemabackend.user.application.dto.JwtDto;
import org.example.cinemabackend.user.application.dto.request.LoginUserRequest;
import org.example.cinemabackend.user.application.dto.request.RegisterUserRequest;
import org.example.cinemabackend.user.core.domain.Role;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.primary.AuthUseCases;
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
    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "User not found";
    private static final String USER_ALREADY_EXISTS_ERROR_MESSAGE = "User already exists";
    private static final String PASSWORD_DOES_NOT_MATCH_ERROR_MESSAGE = "Invalid login credentials";
    private static final String ROLE_DOES_NOT_MATCH_ERROR_MESSAGE = "Role does not match";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;

    @Override
    public JwtDto login(LoginUserRequest loginUserDto) {
        final var user = validateUserExistence(loginUserDto.email());
        checkRolesMatch(loginUserDto.role(), user.getRole());
        checkPasswordsMatch(loginUserDto.password(), user.getPassword());
        final var jwt = createAndEncodeJwt(user);
        return new JwtDto(jwt);
    }


    @Override
    public void register(RegisterUserRequest registerUserDto) {
        checkIfUserAlreadyExists(registerUserDto.email());
        final var encodedPassword = passwordEncoder.encode(registerUserDto.password());
        final var user = new User(registerUserDto.firstName(), registerUserDto.lastName(), registerUserDto.email(),
                encodedPassword, registerUserDto.role());
        userRepository.save(user);
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

    private void checkIfUserAlreadyExists(String username) {
        if (userRepository.existsByEmail(username)) {
            throw new IllegalStateException(USER_ALREADY_EXISTS_ERROR_MESSAGE);
        }
    }

    private void checkRolesMatch(Role loginRole, Role expectedRole) {
        if (!loginRole.equals(expectedRole)) {
            throw new IllegalStateException(ROLE_DOES_NOT_MATCH_ERROR_MESSAGE);
        }
    }

    private User validateUserExistence(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND_ERROR_MESSAGE));
    }
}
