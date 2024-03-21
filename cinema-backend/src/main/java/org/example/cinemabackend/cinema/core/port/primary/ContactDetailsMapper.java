package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.CreateContactDetailsRequest;
import org.example.cinemabackend.cinema.core.domain.ContactDetails;

import java.util.Set;

public interface ContactDetailsMapper {
    Set<ContactDetails> mapCreateContactDetailsToContactDetails(Set<CreateContactDetailsRequest> createContactDetailsRequests);

    ContactDetails mapCreateContactDetailsToContactDetails(CreateContactDetailsRequest createContactDetailsRequest);
}
