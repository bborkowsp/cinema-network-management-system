import {environment} from "../../../assets/environment";
import {HttpClient} from "@angular/common/http";
import {CinemaListResponse} from "../dtos/response/cinema-list.response";
import {map, Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {CinemaDetailsComponent} from "../components/cinema-details/cinema-details.component";
import {CreateCinemaRequest} from "../dtos/request/create-cinema.request";
import {AuthService} from "src/app/user/services/auth.service";

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

  getCinema(name: string): Observable<CinemaDetailsComponent> {
    const url = `${CinemaService.cinemasUrl}/${name}`;
    return this.httpClient.get<CinemaDetailsComponent>(url);
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
    return this.httpClient.get<{ content: string[] }>(url).pipe(
      map((response) => response.content),
    );
  }
}
