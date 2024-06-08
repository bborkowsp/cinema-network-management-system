package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.user.infrastructure.scheme.UserSchema;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @Embedded
    @NotNull
    private ImageSchema image;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScreeningSchema> repertory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ScreeningRoomSchema> screeningRooms = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContactDetailsSchema> contactDetails = new HashSet<>();

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private UserSchema cinemaManager;

    public static CinemaSchema fromCinema(Cinema cinema) {
        return new CinemaSchema(
                cinema.getId(),
                cinema.getName(),
                cinema.getDescription(),
                AddressSchema.fromAddress(cinema.getAddress()),
                ImageSchema.fromImage(cinema.getImage()),
                cinema.getRepertory() == null ? new ArrayList<>() : cinema.getRepertory().stream().map(ScreeningSchema::fromScreening).collect(Collectors.toList()),
                cinema.getScreeningRooms().stream().map(ScreeningRoomSchema::fromScreeningRoom).collect(Collectors.toSet()),
                cinema.getContactDetails().stream().map(ContactDetailsSchema::fromContactDetails).collect(Collectors.toSet()),
                cinema.getCinemaManager() == null ? null : UserSchema.fromUser(cinema.getCinemaManager())
        );
    }

    public Cinema toCinema() {
        final var user = this.cinemaManager == null ? null : this.cinemaManager.toUser();
        return new Cinema(
                this.id,
                this.name,
                this.description,
                this.address.toAddress(),
                this.image.toImage(),
                this.repertory.stream().map(ScreeningSchema::toScreening).collect(Collectors.toList()),
                this.screeningRooms.stream().map(ScreeningRoomSchema::toScreeningRoom).collect(Collectors.toSet()),
                this.contactDetails.stream().map(ContactDetailsSchema::toContactDetails).collect(Collectors.toSet()),
                user
        );
    }
}
