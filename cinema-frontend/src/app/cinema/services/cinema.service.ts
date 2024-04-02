import {environment} from "../../../assets/environment";
import {HttpClient} from "@angular/common/http";
import {CinemaTableResponse} from "../dtos/response/cinema-table.response";
import {map, Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root',
})

export class CinemaService {
  static readonly cinemasUrl = `${environment.apiBaseUrl}/cinemas`;

  constructor(private readonly httpClient: HttpClient) {
  }

  getCinemas(): Observable<CinemaTableResponse[]> {
    return this.httpClient
      .get<{ content: CinemaTableResponse[] }>(CinemaService.cinemasUrl)
      .pipe(
        map((response) => response.content),
      );
  }

  deleteCinema(name: string) {
    const url = `${CinemaService.cinemasUrl}/${name}`;
    return this.httpClient.delete<void>(url);
  }
}
