import {environment} from "../../../assets/environment";
import {ScreeningResponse} from "../dtos/screening.response";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {map, Observable} from "rxjs";
import {CreateScreeningRequest} from "../dtos/create-screening-request";

@Injectable({
  providedIn: 'root',
})
export class ScreeningService {
  static readonly SCREENINGS_API_URL = `${environment.API_BASE_URL}/screenings`;

  constructor(
    private readonly httpClient: HttpClient,
  ) {
  }

  getRepertory() {
    return this.httpClient.get<{ content: ScreeningResponse[] }>(ScreeningService.SCREENINGS_API_URL)
      .pipe(map((response) => response.content));
  }

  getScreening(id: number): Observable<ScreeningResponse> {
    const url = `${ScreeningService.SCREENINGS_API_URL}/id/${id}`;
    return this.httpClient.get<ScreeningResponse>(url);
  }

  createScreening(screening: CreateScreeningRequest) {
    return this.httpClient.post<void>(ScreeningService.SCREENINGS_API_URL, screening);
  }

  updateScreening(id: number, screening: CreateScreeningRequest) {
    const url = `${ScreeningService.SCREENINGS_API_URL}/${id}`;
    return this.httpClient.patch(url, screening);
  }

  deleteScreening(id: number) {
    const url = `${ScreeningService.SCREENINGS_API_URL}/${id}`;
    return this.httpClient.delete<void>(url);
  }
}
