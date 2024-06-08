package org.example.cinemabackend.movie.testdata;

import org.example.cinemabackend.movie.application.dto.request.ProductionDetailsRequest;
import org.example.cinemabackend.movie.core.domain.ProductionDetails;

import java.time.LocalDate;
import java.util.Set;

import static org.example.cinemabackend.movie.testdata.FilmMemberTestDataProvider.*;

public class ProductionDetailsTestDataProvider {

    public static ProductionDetails generateProductionDetails() {
        return new ProductionDetails(
                LocalDate.now(),
                generateFilmMember(),
                generateFilmMembers(),
                Set.of("Language 1", "Language 2"),
                Set.of("Country 1", "Country 2")
        );
    }

    public static ProductionDetailsRequest generateProductionDetailsRequest() {
        return ProductionDetailsRequest.builder()
                .worldPremiereDate(LocalDate.now())
                .director(generateFilmMemberRequest())
                .actors(generateFilmMemberRequests())
                .originalLanguages(Set.of("Language 1", "Language 2"))
                .productionCountries(Set.of("Country 1", "Country 2"))
                .build();
    }
}
