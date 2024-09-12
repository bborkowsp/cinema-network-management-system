package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.user.infrastructure.schema.UserSchema;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CinemaSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "cinema_name", length = 100)
    private String name;

    @Column(nullable = false, length = 2000)
    private String description;

    @Embedded
    @NotNull
    private AddressSchema address;

    @NotNull
    private String image;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ScreeningRoomSchema> screeningRooms = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ContactDetailsSchema> contactDetails = new HashSet<>();

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private UserSchema cinemaManager;

    public static CinemaSchema fromCinema(Cinema cinema) {
        return new CinemaSchema(
                cinema.getId(),
                cinema.getName(),
                cinema.getDescription(),
                AddressSchema.fromAddress(cinema.getAddress()),
                cinema.getImage(),
                cinema.getScreeningRooms().stream().map(ScreeningRoomSchema::fromScreeningRoom).collect(Collectors.toSet()),
                cinema.getContactDetails().stream().map(ContactDetailsSchema::fromContactDetails).collect(Collectors.toSet()),
                cinema.getCinemaManager() == null ? null : UserSchema.fromUser(cinema.getCinemaManager())
        );
    }

    public Cinema toCinema() {
        if (cinemaManager != null && Hibernate.isInitialized(cinemaManager)) {
            final var user = cinemaManager.toUser();
            return new Cinema(
                    this.id,
                    this.name,
                    this.description,
                    this.address.toAddress(),
                    this.image,
                    this.screeningRooms.stream().map(ScreeningRoomSchema::toScreeningRoom).collect(Collectors.toSet()),
                    this.contactDetails.stream().map(ContactDetailsSchema::toContactDetails).collect(Collectors.toSet()),
                    user
            );
        } else {
            return new Cinema(
                    this.id,
                    this.name,
                    this.description,
                    this.address.toAddress(),
                    this.image,
                    this.screeningRooms.stream().map(ScreeningRoomSchema::toScreeningRoom).collect(Collectors.toSet()),
                    this.contactDetails.stream().map(ContactDetailsSchema::toContactDetails).collect(Collectors.toSet()),
                    null
            );
        }
    }
}
