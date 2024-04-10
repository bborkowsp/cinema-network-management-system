export class MovieResponse {
  constructor(
    readonly name: string,
    readonly genre: string,
    readonly duration: string,
    readonly releaseDate: string,
    readonly director: string,
    readonly actors: string,
    readonly description: string,
    readonly rating: string,
    readonly cinema: string,
    readonly screeningRoom: string,
    readonly availableSeats: string,
    readonly unavailableSeats: string
  ) {
  }
}
