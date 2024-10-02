import {PaymentMethod} from "../../components/buy-ticket/enums/payment-method";

export class FinalizePaymentRequest {
  constructor(
    private readonly token: string,
    private readonly paymentMethod: PaymentMethod
  ) {
  }
}
