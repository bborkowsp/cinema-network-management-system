import {environment} from "../../../assets/environment";
import {ScreeningResponse} from "../dtos/screening.response";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {map} from "rxjs";
import {ScreeningRequest} from "../dtos/screening.request";
import {AuthService} from "../../user/services/auth.service";

@Injectable({
  providedIn: 'root',
})
export class ScreeningService {
  static readonly SCREENINGS_API_URL = `${environment.API_BASE_URL}/screenings`;

  constructor(
    private readonly httpClient: HttpClient,
    private readonly authService: AuthService,
  ) {
  }

  getRepertory() {
    const email = this.authService.getLoggedInUserEmail();
    const url = `${ScreeningService.SCREENINGS_API_URL}/${email}`;
    return this.httpClient.get<{ content: ScreeningResponse[] }>(url)
      .pipe(map((response) => response.content));
  }

  deleteScreening(id: number) {
    const url = `${ScreeningService.SCREENINGS_API_URL}/${id}`;
    return this.httpClient.delete<void>(url);
  }

  getScreening(id: number) {
    const url = `${ScreeningService.SCREENINGS_API_URL}/id/${id}`;
    return this.httpClient.get<ScreeningResponse>(url);
  }

  updateScreening(id: number, screening: ScreeningRequest) {
    const url = `${ScreeningService.SCREENINGS_API_URL}/${id}`;
    return this.httpClient.patch(url, screening);
  }

  createScreening(screening: ScreeningRequest) {
    console.log(screening);
    console.log(ScreeningService.SCREENINGS_API_URL);
    return this.httpClient.post<void>(ScreeningService.SCREENINGS_API_URL, screening);
  }
}
