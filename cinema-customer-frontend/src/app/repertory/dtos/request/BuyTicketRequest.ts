export class BuyTicketRequest {
  constructor(
    public readonly movieId: number,
    public readonly selectedSeats: string[],
    public readonly firstName: string,
    public readonly lastName: string,
    public readonly email: string,
    public readonly paymentMethod: string,
  ) {
  }
}
