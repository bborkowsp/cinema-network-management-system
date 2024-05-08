package org.example.cinemabackend.movie.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.movie.core.domain.ProductionDetails;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductionDetailsSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    LocalDate worldPremiereDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    FilmMemberSchema director;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Set<FilmMemberSchema> actors;

    @ElementCollection(fetch = FetchType.EAGER)
    Set<String> originalLanguages;

    @ElementCollection(fetch = FetchType.EAGER)
    Set<String> productionCountries;


    public static ProductionDetailsSchema fromProductionDetails(ProductionDetails productionDetails) {
        return ProductionDetailsSchema.builder()
                .id(productionDetails.getId())
                .worldPremiereDate(productionDetails.getWorldPremiereDate())
                .director(FilmMemberSchema.fromFilmMember(productionDetails.getDirector()))
                .actors(productionDetails.getActors().stream().map(FilmMemberSchema::fromFilmMember)
                        .collect(Collectors.toSet()))
                .originalLanguages(productionDetails.getOriginalLanguages())
                .productionCountries(productionDetails.getProductionCountries())
                .build();
    }

    public ProductionDetails toProductionDetails() {
        return new ProductionDetails(
                this.id,
                this.worldPremiereDate,
                this.director.toFilmMember(),
                this.actors.stream().map(FilmMemberSchema::toFilmMember).collect(Collectors.toSet()),
                this.originalLanguages,
                this.productionCountries
        );
    }
}
