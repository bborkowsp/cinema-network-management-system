export class SeatRequest {
  constructor(
    readonly row: number,
    readonly seat: number,
    readonly seatZone: string,
    readonly seatType = 'AVAILABLE'
  ) {
  }
}
