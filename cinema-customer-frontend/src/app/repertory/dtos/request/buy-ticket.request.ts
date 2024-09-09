export class BuyTicketRequest {
  constructor(
    readonly movieId: number,
    readonly selectedSeats: string[],
    readonly firstName: string,
    readonly lastName: string,
    readonly email: string,
    readonly paymentMethod: string,
  ) {
  }
}
