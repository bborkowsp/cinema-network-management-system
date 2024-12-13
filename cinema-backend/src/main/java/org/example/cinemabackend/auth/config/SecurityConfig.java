package org.example.cinemabackend.auth.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity()
class SecurityConfig {
    private final CorsConfigurationSource corsConfigurationSource;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtConfig jwtConfig;
    private final String API_VERSION = "/v1";
    private final String USER_AUTH_ENDPOINT = API_VERSION + "/auth";
    private final String AUTH_CUSTOMER_ENDPOINT = API_VERSION + "/auth-customer";
    private final String PAYMENT_ENDPOINT = API_VERSION + "/payment";
    private final String CINEMA_ENDPOINT = API_VERSION + "/cinemas";
    private final String SCREENING_ENDPOINT = API_VERSION + "/screenings";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsCustomizer -> corsCustomizer.configurationSource(corsConfigurationSource))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, USER_AUTH_ENDPOINT + "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, AUTH_CUSTOMER_ENDPOINT + "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, AUTH_CUSTOMER_ENDPOINT + "/register").permitAll()
                        .requestMatchers(HttpMethod.POST, AUTH_CUSTOMER_ENDPOINT + "/reset-password-request").permitAll()
                        .requestMatchers(HttpMethod.POST, AUTH_CUSTOMER_ENDPOINT + "/reset-password").permitAll()
                        .requestMatchers(HttpMethod.POST, PAYMENT_ENDPOINT + "/init-payment").permitAll()
                        .requestMatchers(HttpMethod.POST, PAYMENT_ENDPOINT + "/finalize-payment").permitAll()
                        .requestMatchers(HttpMethod.POST, USER_AUTH_ENDPOINT + "/refresh-token").permitAll()
                        .requestMatchers(HttpMethod.GET, API_VERSION + "/verify-account").permitAll()
                        .requestMatchers(HttpMethod.GET, CINEMA_ENDPOINT + "/names").permitAll()
                        .requestMatchers(HttpMethod.GET, SCREENING_ENDPOINT + "/repertory/{cinema}/{date}").permitAll()
                        .requestMatchers(HttpMethod.GET, SCREENING_ENDPOINT + "/details/{title}/{date}").permitAll()
                        .requestMatchers(HttpMethod.GET, API_VERSION + "/logs").permitAll()
                        .requestMatchers(HttpMethod.GET, "/images/**").permitAll()
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtConfig);
    }
}
