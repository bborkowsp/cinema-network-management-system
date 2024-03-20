package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.Address;

@Getter
@Value
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AddressSchema {
    String street;
    String buildingNumber;
    String postalCode;
    String city;
    String country;

    public static AddressSchema fromAddress(Address address) {
        return AddressSchema.builder()
                .street(address.getStreet())
                .buildingNumber(address.getBuildingNumber())
                .postalCode(address.getPostalCode())
                .city(address.getCity())
                .country(address.getCountry())
                .build();
    }

    public Address toAddress() {
        return new Address(
                this.street,
                this.buildingNumber,
                this.postalCode,
                this.city,
                this.country
        );
    }
}
