import {environment} from "../../../assets/environment";
import {HttpClient} from "@angular/common/http";
import {CinemaListResponse} from "../dtos/response/cinema-list.response";
import {map, Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {CreateCinemaRequest} from "../dtos/request/create-cinema.request";
import {CinemaResponse} from "../dtos/response/cinema.response";
import {UpdateCinemaRequest} from "../dtos/request/update-cinema.request";
import {AuthService} from "../../auth/services/auth.service";

@Injectable({
  providedIn: 'root',
})

export class CinemaService {
  static readonly CINEMAS_API_URL = `${environment.API_BASE_URL}/cinemas`;

  constructor(
    private readonly httpClient: HttpClient,
    private readonly authService: AuthService,
  ) {
  }

  getCinemas(): Observable<CinemaListResponse[]> {
    return this.httpClient
      .get<{ content: CinemaListResponse[] }>(CinemaService.CINEMAS_API_URL)
      .pipe(
        map((response) => response.content),
      );
  }

  getCinema(name: string): Observable<CinemaResponse> {
    const url = `${CinemaService.CINEMAS_API_URL}/${name}`;
    return this.httpClient.get<CinemaResponse>(url);
  }

  deleteCinema(name: string) {
    const url = `${CinemaService.CINEMAS_API_URL}/${name}`;
    return this.httpClient.delete<void>(url);
  }

  createCinema(cinema: CreateCinemaRequest) {
    return this.httpClient.post<void>(CinemaService.CINEMAS_API_URL, cinema);
  }

  getAllCinemaNames() {
    const url = `${CinemaService.CINEMAS_API_URL}/names`;
    return this.httpClient.get<{ content: string[] }>(url).pipe(
      map((response) => response.content),
    );
  }

  getAllScreeningRoomNames() {
    const email = this.authService.getLoggedInUserEmail();
    const url = `${CinemaService.CINEMAS_API_URL}/screening-rooms/${email}`;
    return this.httpClient.get<{ content: string[] }>(url).pipe(
      map((response) => response.content),
    );
  }

  updateCinema(cinemaName: string, cinema: UpdateCinemaRequest) {
    console.log(cinema);
    const url = `${CinemaService.CINEMAS_API_URL}/${cinemaName}`;
    return this.httpClient.patch<void>(url, cinema);
  }
}
