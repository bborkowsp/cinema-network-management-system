package org.example.cinemabackend.movie.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.movie.application.dto.response.FilmMemberResponse;
import org.example.cinemabackend.movie.core.domain.FilmMember;
import org.example.cinemabackend.movie.core.port.primary.FilmMemberMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class FilmMemberMapperService implements FilmMemberMapper {

    @Override
    public FilmMemberResponse mapFilmMemberToFilmMemberResponse(FilmMember director) {
        return FilmMemberResponse.builder()
                .firstName(director.getFirstName())
                .lastName(director.getLastName())
                .build();
    }
}
