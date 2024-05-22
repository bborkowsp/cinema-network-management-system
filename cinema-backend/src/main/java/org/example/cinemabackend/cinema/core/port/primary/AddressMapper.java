package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateAddressRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateAddressRequest;
import org.example.cinemabackend.cinema.application.dto.response.AddressResponse;
import org.example.cinemabackend.cinema.core.domain.Address;
import org.springframework.lang.NonNull;


public interface AddressMapper {
    Address mapCreateAddressRequestToAddress(@NonNull CreateAddressRequest address);

    AddressResponse mapAddressToAddressResponse(Address address);

    Address mapUpdateAddressRequestToAddress(UpdateAddressRequest address);
}
