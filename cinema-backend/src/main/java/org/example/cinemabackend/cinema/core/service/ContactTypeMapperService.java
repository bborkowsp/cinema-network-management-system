package org.example.cinemabackend.cinema.core.service;

import org.example.cinemabackend.cinema.application.dto.response.ContactTypeResponse;
import org.example.cinemabackend.cinema.core.domain.ContactType;
import org.example.cinemabackend.cinema.core.port.primary.ContactTypeMapper;
import org.springframework.stereotype.Service;

@Service
class ContactTypeMapperService implements ContactTypeMapper {
    @Override
    public ContactTypeResponse mapContactTypeToContactTypeResponse(ContactType contactType) {
        return ContactTypeResponse.builder()
                .phoneNumber(contactType.getPhoneNumber())
                .email(contactType.getEmail())
                .build();
    }
}