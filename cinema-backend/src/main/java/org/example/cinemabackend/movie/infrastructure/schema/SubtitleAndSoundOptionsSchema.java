package org.example.cinemabackend.movie.infrastructure.schema;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.example.cinemabackend.movie.core.domain.SubtitleAndSoundOptions;

@Getter
@Value
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class SubtitleAndSoundOptionsSchema {
    
    boolean subtitles;
    boolean dubbing;
    boolean voiceOver;
    boolean originalLanguage;

    public static SubtitleAndSoundOptionsSchema fromSubtitleAndSoundOptions(SubtitleAndSoundOptions subtitleAndSoundOptions) {
        return SubtitleAndSoundOptionsSchema.builder()
                .subtitles(subtitleAndSoundOptions.isSubtitles())
                .dubbing(subtitleAndSoundOptions.isDubbing())
                .voiceOver(subtitleAndSoundOptions.isVoiceOver())
                .originalLanguage(subtitleAndSoundOptions.isOriginalLanguage())
                .build();
    }

    public SubtitleAndSoundOptions toSubtitleAndSoundOptions() {
        return new SubtitleAndSoundOptions(
                this.subtitles,
                this.dubbing,
                this.voiceOver,
                this.originalLanguage
        );
    }
}
