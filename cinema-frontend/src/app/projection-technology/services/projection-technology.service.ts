import {environment} from "../../../assets/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {PaginatorRequestParams} from "../../shared/dtos/paginator-request-params";
import {map, Observable} from "rxjs";
import {ProjectionTechnologyPageResponse} from "../dtos/response/projection-technology-page.response";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class ProjectionTechnologyService {
  static readonly projectionTechnologiesUrl = `${environment.apiBaseUrl}/projection-technologies`;

  constructor(private readonly httpClient: HttpClient) {
  }

  deleteProjectionTechnology(name: string): Observable<void> {
    const url = `${ProjectionTechnologyService.projectionTechnologiesUrl}/${name}`;
    return this.httpClient.delete<void>(url);
  }

  getProjectionTechnologies(paginatorRequestParams: PaginatorRequestParams): Observable<ProjectionTechnologyPageResponse> {
    let params = new HttpParams()
      .set('page', paginatorRequestParams.page.toString())
      .set('size', paginatorRequestParams.size.toString());

    if (paginatorRequestParams.sort) {
      params = params.set('sort', paginatorRequestParams.sort.join(','));
    }

    return this.httpClient
      .get<ProjectionTechnologyPageResponse>(ProjectionTechnologyService.projectionTechnologiesUrl, {params})
      .pipe(map((response) => response));
  }
}
