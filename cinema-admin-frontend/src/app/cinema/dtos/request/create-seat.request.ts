export class CreateSeatRequest {
  constructor(
    readonly seatRow: number,
    readonly seatColumn: number,
    readonly seatZone: string,
    readonly seatStatus = 'AVAILABLE'
  ) {
  }
}
