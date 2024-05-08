export class SubtitleAndSoundOptionsRequest {
  constructor(
    readonly subtitles: boolean,
    readonly dubbing: boolean,
    readonly voiceOver: boolean,
    readonly originalLanguage: boolean
  ) {
  }
}
