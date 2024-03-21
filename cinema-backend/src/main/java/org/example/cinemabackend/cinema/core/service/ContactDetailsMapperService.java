package org.example.cinemabackend.cinema.core.service;

import org.example.cinemabackend.cinema.application.dto.request.CreateContactDetailsRequest;
import org.example.cinemabackend.cinema.core.domain.ContactDetails;
import org.example.cinemabackend.cinema.core.port.primary.ContactDetailsMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
class ContactDetailsMapperService implements ContactDetailsMapper {

    @Override
    public Set<ContactDetails> mapCreateContactDetailsToContactDetails(Set<CreateContactDetailsRequest> createContactDetailsRequests) {
        return null;
    }

    @Override
    public ContactDetails mapCreateContactDetailsToContactDetails(CreateContactDetailsRequest createContactDetailsRequest) {
        return null;
    }
}
