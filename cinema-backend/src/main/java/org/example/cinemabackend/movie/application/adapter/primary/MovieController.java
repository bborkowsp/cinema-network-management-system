package org.example.cinemabackend.movie.application.adapter.primary;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.movie.core.port.primary.MovieUseCases;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/movies")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class MovieController {
    private final MovieUseCases movieUseCases;
}
