import {environment} from "../../../assets/environment";
import {HttpClient} from "@angular/common/http";
import {CinemaListResponse} from "../dtos/response/cinema-list.response";
import {map, Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {CreateCinemaRequest} from "../dtos/request/create-cinema.request";
import {AuthService} from "src/app/user/services/auth.service";
import {CinemaResponse} from "../dtos/response/cinema.response";
import {UpdateCinemaRequest} from "../dtos/request/update-cinema.request";

@Injectable({
  providedIn: 'root',
})

export class CinemaService {
  static readonly cinemasUrl = `${environment.apiBaseUrl}/cinemas`;

  constructor(
    private readonly httpClient: HttpClient,
    private readonly authService: AuthService,
  ) {
  }

  getCinemas(): Observable<CinemaListResponse[]> {
    return this.httpClient
      .get<{ content: CinemaListResponse[] }>(CinemaService.cinemasUrl)
      .pipe(
        map((response) => response.content),
      );
  }

  getCinema(name: string): Observable<CinemaResponse> {
    const url = `${CinemaService.cinemasUrl}/${name}`;
    return this.httpClient.get<CinemaResponse>(url);
  }

  deleteCinema(name: string) {
    const url = `${CinemaService.cinemasUrl}/${name}`;
    return this.httpClient.delete<void>(url);
  }

  createCinema(cinema: CreateCinemaRequest) {
    return this.httpClient.post<void>(CinemaService.cinemasUrl, cinema);
  }

  getAllCinemaNames() {
    const url = `${CinemaService.cinemasUrl}/names`;
    return this.httpClient.get<{ content: string[] }>(url).pipe(
      map((response) => response.content),
    );
  }

  getAllScreeningRoomNames() {
    const email = this.authService.getLoggedInUserEmail();
    const url = `${CinemaService.cinemasUrl}/screening-rooms/${email}`;
    console.log(url);
    return this.httpClient.get<{ content: string[] }>(url).pipe(
      map((response) => response.content),
    );
  }

  updateCinema(cinemaName: string, cinema: UpdateCinemaRequest) {
    const url = `${CinemaService.cinemasUrl}/${cinemaName}`;
    return this.httpClient.patch<void>(url, cinema);
  }
}
