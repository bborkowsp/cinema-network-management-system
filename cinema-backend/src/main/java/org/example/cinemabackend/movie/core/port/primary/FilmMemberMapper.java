package org.example.cinemabackend.movie.core.port.primary;

import org.example.cinemabackend.movie.application.dto.response.FilmMemberResponse;
import org.example.cinemabackend.movie.core.domain.FilmMember;

public interface FilmMemberMapper {
    FilmMemberResponse mapFilmMemberToFilmMemberResponse(FilmMember director);
}
