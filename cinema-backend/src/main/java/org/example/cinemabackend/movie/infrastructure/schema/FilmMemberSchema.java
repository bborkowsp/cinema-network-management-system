package org.example.cinemabackend.movie.infrastructure.schema;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.cinemabackend.cinema.infrastructure.config.AbstractEntitySchema;
import org.example.cinemabackend.movie.core.domain.FilmMember;

@Data
@Entity
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FilmMemberSchema extends AbstractEntitySchema<Long> {
    private String firstName;
    private String lastName;

    public static FilmMemberSchema fromFilmMember(FilmMember filmMember) {
        return FilmMemberSchema.builder()
                .firstName(filmMember.getFirstName())
                .lastName(filmMember.getLastName())
                .build();
    }

    public FilmMember toFilmMember() {
        return new FilmMember(
                this.firstName,
                this.lastName
        );
    }
}
