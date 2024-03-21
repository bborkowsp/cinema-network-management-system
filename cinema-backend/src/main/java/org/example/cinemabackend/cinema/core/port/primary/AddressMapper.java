package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.CreateAddressRequest;
import org.example.cinemabackend.cinema.core.domain.Address;
import org.springframework.lang.NonNull;


public interface AddressMapper {
    Address mapCreateAddressRequestToAddress(@NonNull CreateAddressRequest address);
}
