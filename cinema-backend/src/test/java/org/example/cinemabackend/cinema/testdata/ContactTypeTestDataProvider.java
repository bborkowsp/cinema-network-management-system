package org.example.cinemabackend.cinema.testdata;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateContactTypeRequest;
import org.example.cinemabackend.cinema.core.domain.ContactType;

public class ContactTypeTestDataProvider {

    public static CreateContactTypeRequest generateContactTypeRequest() {
        return new CreateContactTypeRequest(
                "+1 123 456 7890",
                "example@example.com"
        );
    }

    public static ContactType generateContactType() {
        return new ContactType(
                "+1 123 456 7890",
                "example@example.com"
        );
    }
}
