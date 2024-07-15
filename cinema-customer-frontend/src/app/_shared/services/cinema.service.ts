import {Injectable} from "@angular/core";
import {environment} from "../../../assets/environment";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root',
})

export class CinemaService {
  static readonly CINEMAS_API_URL = `${environment.API_BASE_URL}/cinemas`;

  constructor(
    private readonly httpClient: HttpClient,
  ) {
  }

  getAllCinemaNames() {
    const url = `${CinemaService.CINEMAS_API_URL}/names`;
    return this.httpClient.get<{ content: string[] }>(url).pipe(
      map((response) => response.content),
    );
  }
}
