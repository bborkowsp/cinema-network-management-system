package org.example.cinemabackend.cinema.core.service;

import org.example.cinemabackend.cinema.application.dto.request.CreateAddressRequest;
import org.example.cinemabackend.cinema.application.dto.response.AddressResponse;
import org.example.cinemabackend.cinema.core.domain.Address;
import org.example.cinemabackend.cinema.core.port.primary.AddressMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;


@Service
class AddressMapperService implements AddressMapper {

    @Override
    public Address mapCreateAddressRequestToAddress(@NonNull CreateAddressRequest address) {
        return new Address(
                address.streetAndBuildingNumber(),
                address.city(),
                address.postalCode(),
                address.country()
        );
    }

    @Override
    public AddressResponse mapAddressToAddressResponse(Address address) {
        return AddressResponse.builder()
                .streetAndBuildingNumber(address.getStreetAndBuildingNumber())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }
}
