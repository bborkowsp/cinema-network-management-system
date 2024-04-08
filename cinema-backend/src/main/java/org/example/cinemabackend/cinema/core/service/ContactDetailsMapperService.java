package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.CreateContactDetailsRequest;
import org.example.cinemabackend.cinema.application.dto.response.ContactDetailsResponse;
import org.example.cinemabackend.cinema.core.domain.ContactDetails;
import org.example.cinemabackend.cinema.core.port.primary.ContactDetailsMapper;
import org.example.cinemabackend.cinema.core.port.primary.ContactTypeMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class ContactDetailsMapperService implements ContactDetailsMapper {

    private final ContactTypeMapper contactTypeMapper;

    @Override
    public Set<ContactDetails> mapCreateContactDetailsToContactDetails(Set<CreateContactDetailsRequest> createContactDetailsRequests) {
        return null;
    }

    @Override
    public ContactDetails mapCreateContactDetailsToContactDetails(CreateContactDetailsRequest createContactDetailsRequest) {
        return null;
    }

    @Override
    public Set<ContactDetailsResponse> mapContactDetailsToContactDetailsResponse(Set<ContactDetails> contactDetails) {
        return contactDetails.stream().map(this::mapContactDetailsToContactDetailsResponse).collect(Collectors.toSet());
    }

    @Override
    public ContactDetailsResponse mapContactDetailsToContactDetailsResponse(ContactDetails contactDetails) {
        return ContactDetailsResponse.builder()
                .department(contactDetails.getDepartment())
                .contactType(contactTypeMapper.mapContactTypeToContactTypeResponse(contactDetails.getContactType()))
                .build();
    }
}
