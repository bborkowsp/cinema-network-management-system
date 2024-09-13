import {Injectable} from "@angular/core";
import {environment} from "../../../assets/environment";
import {HttpClient} from "@angular/common/http";
import {ScreeningDetailsResponse} from "../dtos/response/screening-details.response";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class ScreeningService {
  static readonly SCREENING_SERVICE_API_URL = `${environment.API_BASE_URL}/screenings`;

  constructor(
    private readonly httpClient: HttpClient
  ) {
  }

  getScreeningDetails(title: string, date: string): Observable<ScreeningDetailsResponse> {
    const url = `${ScreeningService.SCREENING_SERVICE_API_URL}/details/${title}/${date}`;
    return this.httpClient.get<ScreeningDetailsResponse>(url);
  }
}
