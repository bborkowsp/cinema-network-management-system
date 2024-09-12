package org.example.cinemabackend.cinema.testdata;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateAddressRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateAddressRequest;
import org.example.cinemabackend.cinema.core.domain.Address;

public class AddressTestDataProvider {
    private static final String STREET = "Street";
    private static final String CITY = "City";
    private static final String POSTAL_CODE = "Postal Code";
    private static final String COUNTRY = "Country";

    public static Address generateAddress() {
        return new Address(
                STREET,
                CITY,
                POSTAL_CODE,
                COUNTRY
        );
    }

    public static CreateAddressRequest generateCreateAddressRequest() {
        return CreateAddressRequest.builder()
                .streetAndBuildingNumber(STREET)
                .city(CITY)
                .postalCode(POSTAL_CODE)
                .country(COUNTRY)
                .build();
    }

    public static UpdateAddressRequest generateUpdateAddressRequest() {
        return UpdateAddressRequest.builder()
                .streetAndBuildingNumber(STREET)
                .city(CITY)
                .postalCode(POSTAL_CODE)
                .country(COUNTRY)
                .build();
    }
}
