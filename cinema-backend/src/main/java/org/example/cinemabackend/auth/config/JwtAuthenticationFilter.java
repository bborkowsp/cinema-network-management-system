package org.example.cinemabackend.auth.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String EMPTY_STRING = "";
    private final JwtConfig jwtConfig;
    private JwtParser jwtParser;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final var authenticationHeader = request.getHeader(jwtConfig.getHeader());

        if (authenticationHeader == null || !authenticationHeader.startsWith(jwtConfig.getPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final var jwt = authenticationHeader.replace(jwtConfig.getPrefix(), EMPTY_STRING);
            final var parsedJwt = jwtParser.parseSignedClaims(jwt);
            final var payload = parsedJwt.getPayload();

            final var username = payload.getSubject();
            final var authorities = ((Collection<?>) payload.get("authorities")).stream()
                    .map(authority -> (GrantedAuthority) () -> (String) ((Map<?, ?>) authority).get("authority"))
                    .toList();

            final var authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (ExpiredJwtException e) {
            // do nothing, because refresh token mechanism is implemented
        } catch (Exception exception) {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @PostConstruct
    private void init() {
        jwtParser = Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build();
    }
}
