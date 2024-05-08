import {environment} from "../../../assets/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {PaginatorRequestParams} from "../../shared/dtos/paginator-request-params";
import {map, Observable} from "rxjs";
import {ProjectionTechnologyPageResponse} from "../dtos/response/projection-technology-page.response";
import {Injectable} from "@angular/core";
import {ProjectionTechnologyRequest} from "../dtos/request/projection-technology.request";
import {ProjectionTechnologyResponse} from "../dtos/response/projection-technology.response";
import {UpdateProjectionTechnologyRequest} from "../dtos/request/update-projection-technology.request";

@Injectable({
  providedIn: 'root',
})
export class ProjectionTechnologyService {
  static readonly projectionTechnologiesUrl = `${environment.apiBaseUrl}/projection-technologies`;

  constructor(private readonly httpClient: HttpClient) {
  }

  deleteProjectionTechnology(technology: string): Observable<void> {
    const url = `${ProjectionTechnologyService.projectionTechnologiesUrl}/${technology}`;
    console.log(url);
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

  createProjectionTechnology(projectTechnology: ProjectionTechnologyRequest): Observable<void> {
    const url = ProjectionTechnologyService.projectionTechnologiesUrl;
    return this.httpClient.post<void>(url, projectTechnology);
  }

  getProjectionTechnology(technology: string): Observable<ProjectionTechnologyRequest> {
    const url = `${ProjectionTechnologyService.projectionTechnologiesUrl}/${technology}`;
    return this.httpClient.get<ProjectionTechnologyRequest>(url);
  }

  getAllProjectionTechnologies(): Observable<ProjectionTechnologyResponse[]> {
    const url = `${ProjectionTechnologyService.projectionTechnologiesUrl}/all`;
    return this.httpClient.get<ProjectionTechnologyResponse[]>(url);
  }

  updateProjectionTechnology(technology: string, projectionTechnology: UpdateProjectionTechnologyRequest) {
    const url = `${ProjectionTechnologyService.projectionTechnologiesUrl}/${technology}`;
    return this.httpClient.patch<void>(url, projectionTechnology);
  }
}
