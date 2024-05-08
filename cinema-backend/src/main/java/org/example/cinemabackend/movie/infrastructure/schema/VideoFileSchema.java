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

    @Column(name = "video_file_url")
    String url;

    public static VideoFileSchema fromVideoFile(VideoFile movieFile) {
        return VideoFileSchema.builder()
                .url(movieFile.getUrl())
                .build();
    }

    public VideoFile toVideoFile() {
        return new VideoFile(
                this.url
        );
    }
}
