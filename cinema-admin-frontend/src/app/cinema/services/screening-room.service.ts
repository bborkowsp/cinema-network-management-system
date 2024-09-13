import {Injectable} from "@angular/core";
import {environment} from "../../../assets/environment";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../auth/services/auth.service";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root',
})

export class ScreeningRoomService {
  static readonly SCREENING_ROOM_API_URL = `${environment.API_BASE_URL}/screening-rooms`;

  constructor(
    private readonly httpClient: HttpClient,
    private readonly authService: AuthService,
  ) {
  }

  getAllScreeningRoomNames() {
    const email = this.authService.getLoggedInUserEmail();
    const url = `${ScreeningRoomService.SCREENING_ROOM_API_URL}/${email}`;
    return this.httpClient.get<{ content: string[] }>(url).pipe(
      map((response) => response.content),
    );
  }
}
