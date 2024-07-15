import {Injectable} from "@angular/core";
import {environment} from "../../../assets/environment";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {ScreeningResponse} from "../../repertory/dtos/response/screening.response";

@Injectable({
  providedIn: 'root',
})
export class ScreeningService {
  static readonly SCREENINGS_API_URL = `${environment.API_BASE_URL}/screenings`;

  constructor(
    private readonly httpClient: HttpClient,
  ) {
  }

  getRepertory(cinema: string | null): Observable<ScreeningResponse[]> {
    const url = `${ScreeningService.SCREENINGS_API_URL}/repertory/${cinema}`;
    console.log(url);
    return this.httpClient.get<{ content: ScreeningResponse[] }>(url).pipe(
      map((response) => response.content),
    );
  }
}
