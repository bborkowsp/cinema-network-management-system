package org.example.cinemabackend.user.infrastructure.scheme;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.cinemabackend.user.core.domain.CinemaManager;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CinemaManagerSchema extends UserSchema {

    public CinemaManagerSchema() {
        this.role = "ROLE_CINEMA_MANAGER";
    }

    public CinemaManagerSchema(CinemaManager cinemaManager) {
        super(cinemaManager.getFirstName(), cinemaManager.getLastName(), cinemaManager.getEmail(), cinemaManager.getPasswordHash(), cinemaManager.getGender());
        this.role = "ROLE_CINEMA_MANAGER";
    }

    public static CinemaManagerSchema fromCinemaManager(CinemaManager cinemaManager) {
        return new CinemaManagerSchema(cinemaManager);
    }

    public CinemaManager toCinemaManager() {
        CinemaManager cinemaManager = new CinemaManager(
                this.getFirstName(),
                this.getLastName(),
                this.getEmail(),
                this.getPasswordHash(),
                this.getGender()
        );

        cinemaManager.setId(this.getId());
        return cinemaManager;
    }

}
