package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateContactTypeRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateContactTypeRequest;
import org.example.cinemabackend.cinema.application.dto.response.ContactTypeResponse;
import org.example.cinemabackend.cinema.core.domain.ContactType;

public interface ContactTypeMapper {
    ContactTypeResponse mapContactTypeToContactTypeResponse(ContactType contactType);

    ContactType mapCreateContactTypeRequestToContactType(CreateContactTypeRequest contactType);

    ContactType mapUpdateContactTypeRequestToContactType(UpdateContactTypeRequest updateContactTypeRequest);
}
