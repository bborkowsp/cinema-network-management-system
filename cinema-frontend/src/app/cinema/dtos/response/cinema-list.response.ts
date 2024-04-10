export class CinemaListResponse {
  constructor(
    readonly name: string,
    readonly cinemaManager: string,
    readonly numberOfScreeningRooms: string,
    readonly numberOfAvailableSeats: string,
    readonly numberOfUnavailableSeats: string
  ) {
  }
}
