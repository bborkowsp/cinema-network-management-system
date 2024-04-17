package org.example.cinemabackend.movie.core.port.primary;

import org.example.cinemabackend.movie.application.dto.response.FilmMemberResponse;
import org.example.cinemabackend.movie.core.domain.FilmMember;

import java.util.Set;

public interface FilmMemberMapper {
    FilmMemberResponse mapFilmMemberToFilmMemberResponse(FilmMember director);

    Set<FilmMemberResponse> mapFilmMembersToFilmMemberResponses(Set<FilmMember> actors);
}
