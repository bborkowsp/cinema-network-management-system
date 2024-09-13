package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.user.infrastructure.schema.UserSchema;
import org.hibernate.Hibernate;

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

    @Column(name = "cinema_name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false, length = 100)
    private String image;

    @NotNull
    @Embedded
    private AddressSchema address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ScreeningRoomSchema> screeningRooms;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ContactDetailsSchema> contactDetails;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private UserSchema cinemaManager;

    public static CinemaSchema fromCinema(Cinema cinema) {
        return CinemaSchema.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .description(cinema.getDescription())
                .image(cinema.getImage())
                .address(AddressSchema.fromAddress(cinema.getAddress()))
                .screeningRooms(cinema.getScreeningRooms().stream().map(ScreeningRoomSchema::fromScreeningRoom).collect(Collectors.toSet()))
                .contactDetails(cinema.getContactDetails().stream().map(ContactDetailsSchema::fromContactDetails).collect(Collectors.toSet()))
                .cinemaManager(cinema.getCinemaManager() == null ? null : UserSchema.fromUser(cinema.getCinemaManager()))
                .build();
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
