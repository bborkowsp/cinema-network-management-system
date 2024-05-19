import {AuthService} from "../../user/services/auth.service";
import {environment} from "../../../assets/environment";
import {ScreeningResponse} from "../dtos/screening.response";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {map} from "rxjs";
import {ScreeningRequest} from "../dtos/screening.request";

@Injectable({
  providedIn: 'root',
})
export class ScreeningService {
  static readonly screeningsUrl = `${environment.apiBaseUrl}/screenings`;

  constructor(
    private readonly authService: AuthService,
    private readonly httpClient: HttpClient,
  ) {
  }

  getRepertory() {
    const email = this.authService.getLoggedInUserEmail();
    const url = `${ScreeningService.screeningsUrl}/${email}`;
    return this.httpClient.get<{ content: ScreeningResponse[] }>(url)
      .pipe(map((response) => response.content));
  }

  deleteScreening(id: number) {
    const url = `${ScreeningService.screeningsUrl}/${id}`;
    return this.httpClient.delete(url);
  }

  getScreening(id: number) {
    const url = `${ScreeningService.screeningsUrl}/id/${id}`;
    return this.httpClient.get<ScreeningResponse>(url);
  }

  updateScreening(id: number, screening: ScreeningRequest) {
    const url = `${ScreeningService.screeningsUrl}/${id}`;
    return this.httpClient.patch(url, screening);
  }

  createScreening(screening: ScreeningRequest) {
    return this.httpClient.post(ScreeningService.screeningsUrl, screening);
  }
}
