package org.example.cinemabackend.movie.application.dto.request;

import lombok.Builder;

@Builder
public record SubtitleAndSoundOptionsRequest(
        boolean subtitles,
        boolean dubbing,
        boolean voiceOver,
        boolean originalLanguage
) {
}
