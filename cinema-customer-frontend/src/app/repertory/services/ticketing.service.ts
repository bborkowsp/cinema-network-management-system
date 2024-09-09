import {environment} from "../../../assets/environment";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {PaypalResponse} from "../dtos/response/paypal.response";
import {BuyTicketRequest} from "../dtos/request/buy-ticket.request";

@Injectable({
  providedIn: 'root',
})
export class TicketingService {
  static readonly TICKETING_API_URL = `${environment.API_BASE_URL}`;
  static readonly PAYPAL_PAYMENT_METHOD_URL = `${TicketingService.TICKETING_API_URL}/paypal`;

  constructor(
    private readonly httpClient: HttpClient
  ) {
  }

  payWithPayPal(buyTicketForm: BuyTicketRequest) {
    const url = `${TicketingService.PAYPAL_PAYMENT_METHOD_URL}/init-payment`;
    return this.httpClient.post<PaypalResponse>(url, buyTicketForm);
  }
}
