package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateContactDetailsRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateContactDetailsRequest;
import org.example.cinemabackend.cinema.application.dto.response.ContactDetailsResponse;
import org.example.cinemabackend.cinema.core.domain.ContactDetails;
import org.example.cinemabackend.cinema.core.port.primary.ContactDetailsMapper;
import org.example.cinemabackend.cinema.core.port.primary.ContactTypeMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class ContactDetailsMapperService implements ContactDetailsMapper {

    private final ContactTypeMapper contactTypeMapper;

    @Override
    public Set<ContactDetails> mapCreateContactDetailsToContactDetails(Set<CreateContactDetailsRequest> createContactDetailsRequests) {
        return createContactDetailsRequests.stream().map(this::mapCreateContactDetailsToContactDetails).collect(Collectors.toSet());
    }

    @Override
    public ContactDetails mapCreateContactDetailsToContactDetails(CreateContactDetailsRequest createContactDetailsRequest) {
        return new ContactDetails(
                createContactDetailsRequest.department(),
                contactTypeMapper.mapCreateContactTypeRequestToContactType(createContactDetailsRequest.contactType())
        );
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

    @Override
    public ContactDetails mapUpdateContactDetailsToContactDetails(UpdateContactDetailsRequest updateContactDetailsRequest, ContactDetails contactDetails) {
        contactDetails.setContactType(contactTypeMapper.mapUpdateContactTypeRequestToContactType(updateContactDetailsRequest.contactType()));
        return contactDetails;
    }

    @Override
    public Set<ContactDetails> mapUpdateContactDetailsToContactDetails(Set<UpdateContactDetailsRequest> updateContactDetailsRequests, Set<ContactDetails> contactDetails) {
        return contactDetails.stream().map(contactDetail -> {
            final var updateContactDetailsRequest = updateContactDetailsRequests.stream()
                    .filter(request -> request.department().equals(contactDetail.getDepartment()))
                    .findFirst()
                    .orElseThrow();
            return mapUpdateContactDetailsToContactDetails(updateContactDetailsRequest, contactDetail);
        }).collect(Collectors.toSet());
    }
}
