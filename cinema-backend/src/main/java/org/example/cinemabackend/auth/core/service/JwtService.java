package org.example.cinemabackend.auth.core.service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.auth.config.JwtConfig;
import org.example.cinemabackend.auth.core.port.primary.JwtUseCases;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
class JwtService implements JwtUseCases {
    private final JwtConfig jwtConfig;
    private final UserRepository userRepository;
    private JwtParser jwtParser;

    @PostConstruct
    private void init() {
        jwtParser = Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .clockSkewSeconds(5000)
                .build();
    }

    @Override
    public String createAndEncodeJwt(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("authorities", user.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(jwtConfig.getSecretKey())
                .compact();
    }

    @Override
    public String createRefreshToken(String jwt) {
        try {
            final var parsedJwt = jwtParser.parseSignedClaims(jwt);
            final var email = parsedJwt.getPayload().getSubject();
            final var user = this.userRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalStateException("User not found"));
            return createAndEncodeJwt(user);

        } catch (Exception exception) {
            throw new IllegalStateException("Invalid token");
        }
    }
}
