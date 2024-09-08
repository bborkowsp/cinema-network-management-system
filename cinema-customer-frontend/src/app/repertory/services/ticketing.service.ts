import {environment} from "../../../assets/environment";
import {Injectable} from "@angular/core";
import {BuyTicketRequest} from "../dtos/request/BuyTicketRequest";
import {HttpClient} from "@angular/common/http";

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
    return this.httpClient.post<void>(url, buyTicketForm);
  }
}
