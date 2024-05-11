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
    String streetAndBuildingNumber;
    String postalCode;
    String city;
    String country;

    public static AddressSchema fromAddress(Address address) {
        return AddressSchema.builder()
                .streetAndBuildingNumber(address.getStreetAndBuildingNumber())
                .postalCode(address.getPostalCode())
                .city(address.getCity())
                .country(address.getCountry())
                .build();
    }

    public Address toAddress() {
        return new Address(
                this.streetAndBuildingNumber,
                this.postalCode,
                this.city,
                this.country
        );
    }
}
