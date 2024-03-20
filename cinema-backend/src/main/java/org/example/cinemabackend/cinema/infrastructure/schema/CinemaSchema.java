package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.infrastructure.config.AbstractEntitySchema;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CinemaSchema extends AbstractEntitySchema<Long> {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, length = 2000)
    private String description;

    @Embedded
    @NotNull
    private AddressSchema address;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private ImageSchema image;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ScreeningSchema> repertory = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ScreeningRoomSchema> screeningRooms = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContactDetailsSchema> contactDetails = new HashSet<>();

    public CinemaSchema(Cinema cinema) {
        this.name = cinema.getName();
        this.description = cinema.getDescription();
        this.address = AddressSchema.fromAddress(cinema.getAddress());
        this.image = ImageSchema.fromImage(cinema.getImage());
        this.repertory = cinema.getRepertory().stream().map(ScreeningSchema::fromScreening).collect(Collectors.toSet());
        this.screeningRooms = cinema.getScreeningRooms().stream().map(ScreeningRoomSchema::fromScreeningRoom).collect(Collectors.toSet());
        this.contactDetails = cinema.getContactDetails().stream().map(ContactDetailsSchema::fromContactDetails).collect(Collectors.toSet());
    }

    public Cinema toCinema() {
        Cinema cinema = new Cinema(
                this.name,
                this.description,
                this.address.toAddress(),
                this.image.toImage(),
                this.repertory.stream().map(ScreeningSchema::toScreening).collect(Collectors.toSet()),
                this.screeningRooms.stream().map(ScreeningRoomSchema::toScreeningRoom).collect(Collectors.toSet()),
                this.contactDetails.stream().map(ContactDetailsSchema::toContactDetails).collect(Collectors.toSet())
        );
        cinema.setId(this.getId());
        return cinema;
    }

}
