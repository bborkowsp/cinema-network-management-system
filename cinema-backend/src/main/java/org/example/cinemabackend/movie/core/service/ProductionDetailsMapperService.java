package org.example.cinemabackend.movie.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.movie.application.dto.request.ProductionDetailsRequest;
import org.example.cinemabackend.movie.application.dto.response.ProductionDetailsResponse;
import org.example.cinemabackend.movie.core.domain.ProductionDetails;
import org.example.cinemabackend.movie.core.port.primary.FilmMemberMapper;
import org.example.cinemabackend.movie.core.port.primary.ProductionDetailsMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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

    @Override
    public ProductionDetails mapCreateProductionDetailsRequestToProductionDetails(ProductionDetailsRequest productionDetailsRequest) {
        return new ProductionDetails(
                productionDetailsRequest.worldPremiereDate(),
                filmMemberMapper.mapCreateFilmMemberRequestToFilmMember(productionDetailsRequest.director()),
                filmMemberMapper.mapCreateFilmMemberRequestsToFilmMember(productionDetailsRequest.actors()),
                productionDetailsRequest.originalLanguages(),
                productionDetailsRequest.productionCountries()
        );
    }

    @Override
    public ProductionDetails mapUpdateProductionDetailsRequestToProductionDetails(ProductionDetailsRequest productionDetailsRequest, ProductionDetails productionDetails) {
        productionDetails.setWorldPremiereDate(productionDetailsRequest.worldPremiereDate());
        productionDetails.setDirector(filmMemberMapper.mapCreateFilmMemberRequestToFilmMember(productionDetailsRequest.director()));
        productionDetails.setActors(filmMemberMapper.mapCreateFilmMemberRequestsToFilmMember(productionDetailsRequest.actors()));
        productionDetails.setOriginalLanguages(productionDetailsRequest.originalLanguages());
        productionDetails.setProductionCountries(productionDetailsRequest.productionCountries());
        return productionDetails;
    }
}
