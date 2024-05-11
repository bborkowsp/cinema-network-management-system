export class CreateSeatRequest {
  constructor(
    readonly rowNumber: number,
    readonly columnNumber: number,
    readonly seatZone: string,
    readonly seatType = 'AVAILABLE'
  ) {
  }
}
