package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.Address;

@Getter
@Value
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AddressSchema {
    String streetAndBuildingNumber;
    String city;
    String postalCode;
    String country;

    public static AddressSchema fromAddress(Address address) {
        return AddressSchema.builder()
                .streetAndBuildingNumber(address.getStreetAndBuildingNumber())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }

    public Address toAddress() {
        return new Address(
                this.streetAndBuildingNumber,
                this.city,
                this.postalCode,
                this.country
        );
    }
}
