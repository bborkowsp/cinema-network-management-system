package org.example.cinemabackend.movie.testdata;

import org.example.cinemabackend.movie.core.domain.SubtitleAndSoundOptions;

public class SubtitleAndSoundOptionTestDataProvider {

    public static SubtitleAndSoundOptions generateSubtitleAndSoundOptions() {
        return new SubtitleAndSoundOptions(true, false, true, false);
    }
}
