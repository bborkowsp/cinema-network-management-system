package org.example.cinemabackend.movie.core.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.movie.core.port.primary.MovieUseCases;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class MovieService implements MovieUseCases {

}
