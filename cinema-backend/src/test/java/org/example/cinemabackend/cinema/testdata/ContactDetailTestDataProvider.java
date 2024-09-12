package org.example.cinemabackend.cinema.testdata;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateContactDetailsRequest;
import org.example.cinemabackend.cinema.core.domain.ContactDetails;

import java.util.Set;

import static org.example.cinemabackend.cinema.testdata.ContactTypeTestDataProvider.generateContactType;
import static org.example.cinemabackend.cinema.testdata.ContactTypeTestDataProvider.generateContactTypeRequest;

public class ContactDetailTestDataProvider {

    public static Set<CreateContactDetailsRequest> generateContactDetailRequests() {
        return Set.of(
                new CreateContactDetailsRequest("Contact Details 1", generateContactTypeRequest()),
                new CreateContactDetailsRequest("Contact Details 2", generateContactTypeRequest()),
                new CreateContactDetailsRequest("Contact Details 3", generateContactTypeRequest())
        );
    }

    public static Set<ContactDetails> generateContactDetails() {
        return Set.of(
                new ContactDetails("Contact Details 1", generateContactType()),
                new ContactDetails("Contact Details 2", generateContactType()),
                new ContactDetails("Contact Details 3", generateContactType())
        );
    }
}
