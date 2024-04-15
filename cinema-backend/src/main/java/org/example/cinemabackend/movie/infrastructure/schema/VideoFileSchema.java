package org.example.cinemabackend.movie.infrastructure.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.example.cinemabackend.movie.core.domain.VideoFile;

@Getter
@Value
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class VideoFileSchema {

    @Column(name = "video_file_name")
    String name;

    @Column(name = "video_file_type")
    String type;

    byte[] video;

    public static VideoFileSchema fromVideoFile(VideoFile movieFile) {
        return VideoFileSchema.builder()
                .name(movieFile.getName())
                .type(movieFile.getType())
                .video(movieFile.getVideo())
                .build();
    }

    public VideoFile toVideoFile() {
        return new VideoFile(
                this.name,
                this.type,
                this.video
        );
    }
}
