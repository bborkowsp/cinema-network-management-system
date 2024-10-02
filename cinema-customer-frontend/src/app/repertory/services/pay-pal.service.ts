import {environment} from "../../../assets/environment";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {PaypalResponse} from "../dtos/response/paypal.response";
import {BuyTicketRequest} from "../dtos/request/buy-ticket.request";
import {FinalizePaymentRequest} from "../dtos/request/finalize-payment.request";

@Injectable({
  providedIn: 'root',
})
export class PayPalService {
  static readonly TICKETING_ENDPOINT_URL = `${environment.API_BASE_URL}`;
  static readonly PAYPAL_ENDPOINT_URL = `${PayPalService.TICKETING_ENDPOINT_URL}/payment`;

  constructor(
    private readonly httpClient: HttpClient
  ) {
  }

  payWithPayPal(buyTicketForm: BuyTicketRequest) {
    const url = `${PayPalService.PAYPAL_ENDPOINT_URL}/init-payment`;
    return this.httpClient.post<PaypalResponse>(url, buyTicketForm);
  }

  sendCompletePayPalPaymentRequest(finalizePaymentRequest: FinalizePaymentRequest) {
    const url = `${PayPalService.PAYPAL_ENDPOINT_URL}/finalize-payment`;
    return this.httpClient.post<void>(url, finalizePaymentRequest);
  }
}
