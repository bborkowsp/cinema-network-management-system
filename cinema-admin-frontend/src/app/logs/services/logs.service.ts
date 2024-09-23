import {environment} from "../../../assets/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {PaginatorRequestParams} from "../../_shared/dtos/paginator-request-params";
import {map, Observable} from "rxjs";
import {LogPageResponse} from "../dto/log-page.response";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class LogsService {
  static readonly LOGS_API_URL = `${environment.API_BASE_URL}/logs`;

  constructor(
    private readonly httpClient: HttpClient
  ) {
  }

  getLogs(paginatorRequestParams: PaginatorRequestParams): Observable<LogPageResponse> {
    let params = new HttpParams()
      .set('page', paginatorRequestParams.page.toString())
      .set('size', paginatorRequestParams.size.toString());

    if (paginatorRequestParams.sort) {
      params = params.set('sort', paginatorRequestParams.sort.join(','));
    }

    return this.httpClient
      .get<LogPageResponse>(LogsService.LOGS_API_URL, {params})
      .pipe(map((response) => response));
  }
}
