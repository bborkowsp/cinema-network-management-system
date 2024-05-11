import {map, Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../assets/environment";
import {PaginatorRequestParams} from "../../shared/dtos/paginator-request-params";
import {CinemaManagerResponse} from "../dtos/response/cinema-manager.response";
import {CinemaManagerPageResponse} from "../dtos/response/cinema-manager-page.response";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  static readonly usersUrl = `${environment.apiBaseUrl}/users`;

  constructor(private httpClient: HttpClient) {
  }

  getCinemaManagers(paginatorRequestParams: PaginatorRequestParams): Observable<CinemaManagerPageResponse> {
    let params = new HttpParams()
      .set('page', paginatorRequestParams.page.toString())
      .set('size', paginatorRequestParams.size.toString());

    if (paginatorRequestParams.sort) {
      params = params.set('sort', paginatorRequestParams.sort.join(','));
    }

    return this.httpClient
      .get<CinemaManagerPageResponse>(UserService.usersUrl, {params})
      .pipe(map((response) => response));
  }

  getCinemaManager(email: string): Observable<CinemaManagerResponse> {
    const url = `${UserService.usersUrl}/${email}`;
    return this.httpClient.get<CinemaManagerResponse>(url);
  }


  deleteCinemaManager(email: string) {
    const url = `${UserService.usersUrl}/${email}`;
    return this.httpClient.delete<void>(url);
  }


}
