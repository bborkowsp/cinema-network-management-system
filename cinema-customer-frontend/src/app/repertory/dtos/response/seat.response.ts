export class SeatResponse {
  constructor(
    readonly id: number,
    readonly seatRow: number,
    readonly seatColumn: number,
    readonly seatZone: string,
    readonly seatStatus: string,
  ) {
  }
}

