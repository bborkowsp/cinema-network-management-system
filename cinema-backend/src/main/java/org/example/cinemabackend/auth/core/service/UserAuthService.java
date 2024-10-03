package org.example.cinemabackend.auth.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.auth.application.dto.JwtDto;
import org.example.cinemabackend.auth.application.dto.request.LoginUserRequest;
import org.example.cinemabackend.auth.core.port.primary.JwtUseCases;
import org.example.cinemabackend.auth.core.port.primary.UserAuthUseCases;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
class UserAuthService implements UserAuthUseCases, UserDetailsService {
    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "Invalid login credentials";
    private static final String PASSWORD_DOES_NOT_MATCH_ERROR_MESSAGE = "Invalid login credentials";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUseCases jwtUseCases;

    @Override
    public JwtDto login(LoginUserRequest loginUserDto) {
        final var user = validateUserExistence(loginUserDto.email());
        checkPasswordsMatch(loginUserDto.password(), user.getPassword());
        final var jwt = jwtUseCases.createAndEncodeJwt(user);
        return new JwtDto(jwt);
    }


    private User validateUserExistence(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND_ERROR_MESSAGE));
    }

    private void checkPasswordsMatch(String password, String encodedPassword) {
        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new IllegalStateException(PASSWORD_DOES_NOT_MATCH_ERROR_MESSAGE);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_ERROR_MESSAGE));
    }
}
