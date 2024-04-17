package org.example.cinemabackend.movie.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.movie.application.dto.response.ProductionDetailsResponse;
import org.example.cinemabackend.movie.core.domain.ProductionDetails;
import org.example.cinemabackend.movie.core.port.primary.FilmMemberMapper;
import org.example.cinemabackend.movie.core.port.primary.ProductionDetailsMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class ProductionDetailsMapperService implements ProductionDetailsMapper {

    private final FilmMemberMapper filmMemberMapper;

    @Override
    public ProductionDetailsResponse mapProductionDetailsToProductionDetailsResponse(ProductionDetails productionDetails) {
        return ProductionDetailsResponse.builder()
                .worldPremiereDate(productionDetails.getWorldPremiereDate())
                .director(filmMemberMapper.mapFilmMemberToFilmMemberResponse(productionDetails.getDirector()))
                .actors(filmMemberMapper.mapFilmMembersToFilmMemberResponses(productionDetails.getActors()))
                .originalLanguages(productionDetails.getOriginalLanguages())
                .productionCountries(productionDetails.getProductionCountries())
                .build();
    }
}
