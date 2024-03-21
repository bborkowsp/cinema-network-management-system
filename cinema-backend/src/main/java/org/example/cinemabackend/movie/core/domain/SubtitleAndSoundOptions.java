package org.example.cinemabackend.movie.core.domain;

public class SubtitleAndSoundOptions {
    private boolean subtitles;
    private boolean dubbing;
    private boolean voiceOver;
    private boolean originalLanguage;

    public SubtitleAndSoundOptions(boolean subtitles, boolean dubbing, boolean voiceOver, boolean originalLanguage) {
        this.subtitles = subtitles;
        this.dubbing = dubbing;
        this.voiceOver = voiceOver;
        this.originalLanguage = originalLanguage;
    }

    public boolean isSubtitles() {
        return subtitles;
    }

    public boolean isDubbing() {
        return dubbing;
    }

    public boolean isVoiceOver() {
        return voiceOver;
    }

    public boolean isOriginalLanguage() {
        return originalLanguage;
    }
}
