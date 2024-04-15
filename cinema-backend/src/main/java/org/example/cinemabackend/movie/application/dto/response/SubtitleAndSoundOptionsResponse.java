package org.example.cinemabackend.movie.application.dto.response;

import lombok.Builder;

@Builder
public record SubtitleAndSoundOptionsResponse(
        boolean subtitles,
        boolean dubbing,
        boolean voiceOver,
        boolean originalLanguage
) {
}
