import {AuthService} from "../../user/services/auth.service";
import {environment} from "../../../assets/environment";
import {ScreeningResponse} from "../dtos/screening.response";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {map} from "rxjs";

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

}
