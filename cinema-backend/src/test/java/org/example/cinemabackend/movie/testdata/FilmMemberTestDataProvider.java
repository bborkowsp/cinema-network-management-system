package org.example.cinemabackend.movie.testdata;

import org.example.cinemabackend.movie.application.dto.request.FilmMemberRequest;
import org.example.cinemabackend.movie.core.domain.FilmMember;

import java.util.Set;

public class FilmMemberTestDataProvider {

    public static FilmMember generateFilmMember() {
        return new FilmMember("First Name 1", "Last Name");
    }

    public static Set<FilmMember> generateFilmMembers() {
        return Set.of(
                new FilmMember("First Name 2", "Last Name"),
                new FilmMember("First Name 3", "Last Name"),
                new FilmMember("First Name 4", "Last Name")
        );
    }

    public static FilmMemberRequest generateFilmMemberRequest() {
        return FilmMemberRequest.builder()
                .firstName("First Name 1")
                .lastName("Last Name")
                .build();
    }

    public static Set<FilmMemberRequest> generateFilmMemberRequests() {
        return Set.of(
                new FilmMemberRequest("First Name 2", "Last Name"),
                new FilmMemberRequest("First Name 3", "Last Name")
        );
    }
}
