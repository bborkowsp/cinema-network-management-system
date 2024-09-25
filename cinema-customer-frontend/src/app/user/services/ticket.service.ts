import {Injectable} from "@angular/core";
import {environment} from "../../../assets/environment";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {TicketResponse} from "../dtos/response/ticket.response";

@Injectable({
  providedIn: 'root',
})
export class TicketService {
  static readonly USERS_API_URL = `${environment.API_BASE_URL}/tickets`;

  constructor(
    private httpClient: HttpClient
  ) {
  }

  getTickets(): Observable<TicketResponse[]> {
    const url = `${TicketService.USERS_API_URL}`;
    return this.httpClient.get<{ content: TicketResponse[] }>(url).pipe(
      map(response => response.content)
    );
  }
}
