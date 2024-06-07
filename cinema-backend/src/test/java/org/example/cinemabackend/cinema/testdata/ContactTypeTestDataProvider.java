package org.example.cinemabackend.cinema.testdata;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateContactTypeRequest;

public class ContactTypeTestDataProvider {

    public static CreateContactTypeRequest generateContactTypeRequest() {
        return new CreateContactTypeRequest(
                "+1 123 456 7890",
                "example@example.com"
        );
    }
}
