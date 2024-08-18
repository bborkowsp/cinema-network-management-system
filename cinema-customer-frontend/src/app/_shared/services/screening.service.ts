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
    return this.httpClient.get<{ content: ScreeningResponse[] }>(url).pipe(
      map((response) => response.content),
    );
  }

  getRepertoryAtSpecificDate(cinema: string | null, date: Date): Observable<ScreeningResponse[]> {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const formattedDate = `${year}-${month}-${day}`;
    const url = `${ScreeningService.SCREENINGS_API_URL}/repertory/${cinema}/${formattedDate}`;
    console.log(url);
    return this.httpClient.get<{ content: ScreeningResponse[] }>(url).pipe(
      map((response) => response.content),
    );
  }
}
