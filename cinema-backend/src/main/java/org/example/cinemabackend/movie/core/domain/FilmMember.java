package org.example.cinemabackend.movie.core.domain;

public class FilmMember {
    private Long id;
    private String firstName;
    private String lastName;

    public FilmMember(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public FilmMember(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getId() {
        return id;
    }
}
