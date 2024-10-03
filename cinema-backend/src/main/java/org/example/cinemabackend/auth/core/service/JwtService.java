package org.example.cinemabackend.auth.core.service;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.auth.config.JwtConfig;
import org.example.cinemabackend.auth.core.port.primary.JwtUseCases;
import org.example.cinemabackend.user.core.domain.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
class JwtService implements JwtUseCases {
    private final JwtConfig jwtConfig;

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
}
