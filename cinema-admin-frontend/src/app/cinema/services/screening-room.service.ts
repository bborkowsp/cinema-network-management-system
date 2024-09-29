import {Injectable} from "@angular/core";
import {environment} from "../../../assets/environment";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root',
})

export class ScreeningRoomService {
  private static readonly SCREENING_ROOM_ENDPOINT_URL = `${environment.API_BASE_URL}/screening-rooms`;

  constructor(
    private readonly httpClient: HttpClient,
  ) {
  }

  getAllScreeningRoomNames() {
    return this.httpClient.get<{ content: string[] }>(ScreeningRoomService.SCREENING_ROOM_ENDPOINT_URL).pipe(
      map((response) => response.content),
    );
  }
}
