package org.example.cinemabackend.movie.core.service;

import org.example.cinemabackend.movie.application.dto.request.FilmMemberRequest;
import org.example.cinemabackend.movie.application.dto.response.FilmMemberResponse;
import org.example.cinemabackend.movie.core.domain.FilmMember;
import org.example.cinemabackend.movie.core.port.primary.FilmMemberMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
class FilmMemberMapperService implements FilmMemberMapper {

    @Override
    public FilmMemberResponse mapFilmMemberToFilmMemberResponse(FilmMember director) {
        return FilmMemberResponse.builder()
                .firstName(director.getFirstName())
                .lastName(director.getLastName())
                .build();
    }

    @Override
    public Set<FilmMemberResponse> mapFilmMembersToFilmMemberResponses(Set<FilmMember> actors) {
        return actors.stream().map(this::mapFilmMemberToFilmMemberResponse).collect(Collectors.toSet());
    }

    @Override
    public FilmMember mapCreateFilmMemberRequestToFilmMember(FilmMemberRequest filmMember) {
        return new FilmMember(filmMember.firstName(), filmMember.lastName());
    }

    @Override
    public Set<FilmMember> mapCreateFilmMemberRequestsToFilmMember(Set<FilmMemberRequest> actors) {
        return actors.stream().map(this::mapCreateFilmMemberRequestToFilmMember).collect(Collectors.toSet());
    }
}
