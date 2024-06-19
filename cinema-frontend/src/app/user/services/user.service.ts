import {map, Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../assets/environment";
import {PaginatorRequestParams} from "../../_shared/dtos/paginator-request-params";
import {Injectable} from "@angular/core";
import {CinemaManagerResponse} from "../dtos/response/cinema-manager.response";
import {CinemaManagerRequest} from "../dtos/request/cinema-manager.request";
import {UserPageResponse} from "../dtos/response/user-page.response";
import {CinemaManagerPageResponse} from "../dtos/response/cinema-manager-page.response";
import {CinemaNetworkManagerRequest} from "../dtos/request/cinema-network-manager.request";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  static readonly USERS_API_URL = `${environment.API_BASE_URL}/users`;
  static readonly CINEMA_MANAGERS_ENDPOINT_PREFIX = `${UserService.USERS_API_URL}/cinema-managers`;
  static readonly CINEMA_NETWORK_MANAGERS_ENDPOINT_PREFIX = `${UserService.USERS_API_URL}/cinema-network-managers`;

  constructor(private httpClient: HttpClient) {
  }

  getUsers(paginatorRequestParams: PaginatorRequestParams): Observable<UserPageResponse> {
    let params = new HttpParams()
      .set('page', paginatorRequestParams.page.toString())
      .set('size', paginatorRequestParams.size.toString());

    if (paginatorRequestParams.sort) {
      params = params.set('sort', paginatorRequestParams.sort.join(','));
    }

    return this.httpClient
      .get<UserPageResponse>(UserService.USERS_API_URL, {params})
      .pipe(map((response) => response));
  }

  getCinemaManagers(paginatorRequestParams: PaginatorRequestParams): Observable<CinemaManagerPageResponse> {
    let params = new HttpParams()
      .set('page', paginatorRequestParams.page.toString())
      .set('size', paginatorRequestParams.size.toString());

    if (paginatorRequestParams.sort) {
      params = params.set('sort', paginatorRequestParams.sort.join(','));
    }
    console.log("bb");

    console.log(UserService.CINEMA_MANAGERS_ENDPOINT_PREFIX);

    return this.httpClient
      .get<CinemaManagerPageResponse>(UserService.CINEMA_MANAGERS_ENDPOINT_PREFIX, {params})
      .pipe(map((response) => response));
  }


  getCinemaManagersList() {
    return this.httpClient.get<{ content: CinemaManagerResponse[] }>(`${UserService.USERS_API_URL}/cinema-managers`)
      .pipe(map((response) => response.content));
  }

  getCinemaManager(email: string): Observable<CinemaManagerResponse> {
    const url = `${UserService.CINEMA_MANAGERS_ENDPOINT_PREFIX}/${email}`;
    return this.httpClient.get<CinemaManagerResponse>(url);
  }

  createCinemaManager(cinemaManager: CinemaManagerRequest) {
    return this.httpClient.post<void>(UserService.CINEMA_MANAGERS_ENDPOINT_PREFIX, cinemaManager);
  }

  updateCinemaManager(cinemaManagerEmail: string, cinemaManager: CinemaManagerRequest) {
    const url = `${UserService.CINEMA_MANAGERS_ENDPOINT_PREFIX}/${cinemaManagerEmail}`;
    return this.httpClient.patch<void>(url, cinemaManager);
  }


  deleteCinemaNetworkManager(email: string) {
    const url = `${UserService.CINEMA_NETWORK_MANAGERS_ENDPOINT_PREFIX}/${email}`;
    return this.httpClient.delete<void>(url);
  }

  updateCinemaNetworkManager(cinemaManagerEmail: string, cinemaManager: CinemaNetworkManagerRequest) {
    const url = `${UserService.CINEMA_NETWORK_MANAGERS_ENDPOINT_PREFIX}/${cinemaManagerEmail}`;
    return this.httpClient.patch<void>(url, cinemaManager);
  }

  createCinemaNetworkManager(cinemaManager: CinemaNetworkManagerRequest) {
    return this.httpClient.post<void>(UserService.CINEMA_NETWORK_MANAGERS_ENDPOINT_PREFIX, cinemaManager);
  }

  deleteCinemaManager(email: string) {
    const url = `${UserService.CINEMA_MANAGERS_ENDPOINT_PREFIX}/${email}`;
    return this.httpClient.delete<void>(url);
  }
}
