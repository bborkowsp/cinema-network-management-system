package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.example.cinemabackend.movie.core.domain.Image;

@Getter
@Value
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ImageSchema {

    String name;

    String type;

    byte[] image;

    public static ImageSchema fromImage(Image image) {
        return ImageSchema.builder()
                .name(image.getName())
                .type(image.getType())
                .image(image.getImage())
                .build();
    }

    public Image toImage() {
        return new Image(
                this.name,
                this.type,
                this.image
        );
    }
}
