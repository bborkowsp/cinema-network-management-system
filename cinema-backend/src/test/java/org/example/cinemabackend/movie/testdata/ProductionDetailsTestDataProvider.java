package org.example.cinemabackend.movie.testdata;

import org.example.cinemabackend.movie.core.domain.ProductionDetails;

import java.time.LocalDate;
import java.util.Set;

import static org.example.cinemabackend.movie.testdata.FilmMemberTestDataProvider.generateFilmMember;
import static org.example.cinemabackend.movie.testdata.FilmMemberTestDataProvider.generateFilmMembers;

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
}
