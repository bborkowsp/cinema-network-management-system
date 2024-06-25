export class SubtitleAndSoundOptionsResponse {
  constructor(
    readonly subtitles: boolean,
    readonly dubbing: boolean,
    readonly voiceOver: boolean,
    readonly originalLanguage: boolean
  ) {
  }
}
