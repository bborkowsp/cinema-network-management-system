package org.example.cinemabackend.movie.infrastructure.schema;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.example.cinemabackend.movie.core.domain.FilmMember;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FilmMemberSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    public static FilmMemberSchema fromFilmMember(FilmMember filmMember) {
        return FilmMemberSchema.builder()
                .id(filmMember.getId())
                .firstName(filmMember.getFirstName())
                .lastName(filmMember.getLastName())
                .build();
    }

    public FilmMember toFilmMember() {
        return new FilmMember(
                this.id,
                this.firstName,
                this.lastName
        );
    }
}
