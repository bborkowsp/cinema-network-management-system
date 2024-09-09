export class PaypalResponse {
  constructor(
    readonly status: string,
    readonly orderId: string,
    readonly redirectUrl: string,
  ) {
  }
}
