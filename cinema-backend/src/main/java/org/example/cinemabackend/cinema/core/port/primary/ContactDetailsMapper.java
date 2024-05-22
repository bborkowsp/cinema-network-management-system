package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateContactDetailsRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateContactDetailsRequest;
import org.example.cinemabackend.cinema.application.dto.response.ContactDetailsResponse;
import org.example.cinemabackend.cinema.core.domain.ContactDetails;

import java.util.Set;

public interface ContactDetailsMapper {
    Set<ContactDetails> mapCreateContactDetailsToContactDetails(Set<CreateContactDetailsRequest> createContactDetailsRequests);

    ContactDetails mapCreateContactDetailsToContactDetails(CreateContactDetailsRequest createContactDetailsRequest);

    Set<ContactDetailsResponse> mapContactDetailsToContactDetailsResponse(Set<ContactDetails> contactDetails);

    ContactDetailsResponse mapContactDetailsToContactDetailsResponse(ContactDetails contactDetails);

    ContactDetails mapUpdateContactDetailsToContactDetails(UpdateContactDetailsRequest updateContactDetailsRequest, ContactDetails contactDetails);

    Set<ContactDetails> mapUpdateContactDetailsToContactDetails(Set<UpdateContactDetailsRequest> updateContactDetailsRequests, Set<ContactDetails> contactDetails);
}
