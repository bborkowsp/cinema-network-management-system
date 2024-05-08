package org.example.cinemabackend.movie.core.service;

import org.example.cinemabackend.movie.application.dto.request.SubtitleAndSoundOptionsRequest;
import org.example.cinemabackend.movie.application.dto.response.SubtitleAndSoundOptionsResponse;
import org.example.cinemabackend.movie.core.domain.SubtitleAndSoundOptions;
import org.example.cinemabackend.movie.core.port.primary.SubtitleAndSoundOptionsMapper;
import org.springframework.stereotype.Service;

@Service
class SubtitleAndSoundOptionsMapperService implements SubtitleAndSoundOptionsMapper {
    @Override
    public SubtitleAndSoundOptionsResponse mapSubtitleAndSoundOptionsToSubtitleAndSoundOptionsResponse(SubtitleAndSoundOptions subtitleAndSoundOptions) {
        return SubtitleAndSoundOptionsResponse.builder()
                .subtitles(subtitleAndSoundOptions.isSubtitles())
                .dubbing(subtitleAndSoundOptions.isDubbing())
                .voiceOver(subtitleAndSoundOptions.isVoiceOver())
                .originalLanguage(subtitleAndSoundOptions.isOriginalLanguage())
                .build();
    }

    @Override
    public SubtitleAndSoundOptions mapCreateSubtitleAndSoundOptionsRequestToSubtitleAndSoundOptions(SubtitleAndSoundOptionsRequest subtitleAndSoundOptionsRequest) {
        return new SubtitleAndSoundOptions(subtitleAndSoundOptionsRequest.subtitles(), subtitleAndSoundOptionsRequest.dubbing(), subtitleAndSoundOptionsRequest.voiceOver(), subtitleAndSoundOptionsRequest.originalLanguage());
    }
}
