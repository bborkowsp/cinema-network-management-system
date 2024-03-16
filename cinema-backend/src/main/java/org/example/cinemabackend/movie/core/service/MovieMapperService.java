package org.example.cinemabackend.movie.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.movie.core.port.primary.MovieMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class MovieMapperService implements MovieMapper {
}
