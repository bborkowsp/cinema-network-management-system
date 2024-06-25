export class ScreeningRequest {
  constructor(
    readonly movieTitle: string,
    readonly startTime: Date,
    readonly endTime: Date,
    readonly screeningRoom: string,
    readonly email: string,
  ) {
  }
}
