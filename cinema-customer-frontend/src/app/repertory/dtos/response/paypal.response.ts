export class PaypalResponse {
  constructor(
    public readonly status: string,
    public readonly orderId: string,
    public readonly redirectUrl: string,
  ) {
  }
}
