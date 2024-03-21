package org.example.cinemabackend.movie.infrastructure.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.example.cinemabackend.movie.core.domain.Description;

@Getter
@Value
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class DescriptionSchema {
    @Column(length = 500)
    String shortDescription;

    @Column(length = 5000)
    String longDescription;

    public static DescriptionSchema fromDescription(Description description) {
        return DescriptionSchema.builder()
                .shortDescription(description.getShortDescription())
                .longDescription(description.getLongDescription())
                .build();
    }

    public Description toDescription() {
        return new Description(
                this.shortDescription,
                this.longDescription
        );
    }
}
