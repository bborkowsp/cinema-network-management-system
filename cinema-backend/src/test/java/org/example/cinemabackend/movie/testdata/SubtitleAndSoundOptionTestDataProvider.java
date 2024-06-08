package org.example.cinemabackend.movie.testdata;

import org.example.cinemabackend.movie.application.dto.request.SubtitleAndSoundOptionsRequest;
import org.example.cinemabackend.movie.core.domain.SubtitleAndSoundOptions;

public class SubtitleAndSoundOptionTestDataProvider {

    public static SubtitleAndSoundOptions generateSubtitleAndSoundOptions() {
        return new SubtitleAndSoundOptions(true, false, true, false);
    }

    public static SubtitleAndSoundOptionsRequest generateSubtitleAndSoundOptionsRequest() {
        return SubtitleAndSoundOptionsRequest.builder()
                .subtitles(true)
                .dubbing(false)
                .voiceOver(false)
                .originalLanguage(true)
                .build();
    }
}
