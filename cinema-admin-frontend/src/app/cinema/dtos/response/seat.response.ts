export class SeatResponse {
  constructor(
    readonly seatRow: number,
    readonly seatColumn: number,
    readonly seatZone: string,
    readonly seatType: string,
  ) {
  }
}
