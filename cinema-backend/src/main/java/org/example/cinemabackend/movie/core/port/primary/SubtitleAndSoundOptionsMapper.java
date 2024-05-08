package org.example.cinemabackend.movie.core.port.primary;

import org.example.cinemabackend.movie.application.dto.request.SubtitleAndSoundOptionsRequest;
import org.example.cinemabackend.movie.application.dto.response.SubtitleAndSoundOptionsResponse;
import org.example.cinemabackend.movie.core.domain.SubtitleAndSoundOptions;

public interface SubtitleAndSoundOptionsMapper {
    SubtitleAndSoundOptionsResponse mapSubtitleAndSoundOptionsToSubtitleAndSoundOptionsResponse(SubtitleAndSoundOptions subtitleAndSoundOptions);

    SubtitleAndSoundOptions mapCreateSubtitleAndSoundOptionsRequestToSubtitleAndSoundOptions(SubtitleAndSoundOptionsRequest subtitleAndSoundOptionsRequest);
}
