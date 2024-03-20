package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.example.cinemabackend.cinema.infrastructure.config.AbstractEntitySchema;
import org.example.cinemabackend.movie.core.domain.Image;

@Data
@Entity
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageSchema extends AbstractEntitySchema<Long> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private byte[] image;

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
