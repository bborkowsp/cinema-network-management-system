package org.example.cinemabackend.movie.core.service;

import org.example.cinemabackend.movie.application.dto.response.DescriptionResponse;
import org.example.cinemabackend.movie.core.domain.Description;
import org.example.cinemabackend.movie.core.port.primary.DescriptionMapper;
import org.springframework.stereotype.Service;

@Service
class DescriptionMapperService implements DescriptionMapper {

    @Override
    public DescriptionResponse mapDescriptionToDescriptionResponse(Description description) {
        return DescriptionResponse.builder()
                .shortDescription(description.getShortDescription())
                .longDescription(description.getLongDescription())
                .build();
    }
}
