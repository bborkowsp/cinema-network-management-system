package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.user.infrastructure.scheme.UserSchema;

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

    @Embedded
    private ImageSchema image;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ScreeningSchema> repertory = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ScreeningRoomSchema> screeningRooms = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ContactDetailsSchema> contactDetails = new HashSet<>();

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private UserSchema cinemaManager;

    public static CinemaSchema fromCinema(Cinema cinema) {
        return CinemaSchema.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .description(cinema.getDescription())
                .address(AddressSchema.fromAddress(cinema.getAddress()))
                .image(ImageSchema.fromImage(cinema.getImage()))
                .repertory(cinema.getRepertory() == null ? new HashSet<>() : cinema.getRepertory().stream().map(ScreeningSchema::fromScreening).collect(Collectors.toSet()))
                .screeningRooms(cinema.getScreeningRooms().stream().map(ScreeningRoomSchema::fromScreeningRoom).collect(Collectors.toSet()))
                .contactDetails(cinema.getContactDetails().stream().map(ContactDetailsSchema::fromContactDetails).collect(Collectors.toSet()))
                .cinemaManager(UserSchema.fromUser(cinema.getCinemaManager()))
                .build();
    }

    public Cinema toCinema() {
        Cinema cinema = new Cinema(
                this.id,
                this.name,
                this.description,
                this.address.toAddress(),
                this.image.toImage(),
                this.repertory.stream().map(ScreeningSchema::toScreening).collect(Collectors.toSet()),
                this.screeningRooms.stream().map(ScreeningRoomSchema::toScreeningRoom).collect(Collectors.toSet()),
                this.contactDetails.stream().map(ContactDetailsSchema::toContactDetails).collect(Collectors.toSet()),
                this.cinemaManager.toUser()
        );
        return cinema;
    }

}
