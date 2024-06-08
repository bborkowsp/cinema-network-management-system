import {environment} from "../../../assets/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {PaginatorRequestParams} from "../../shared/dtos/paginator-request-params";
import {map, Observable} from "rxjs";
import {ProjectionTechnologyPageResponse} from "../dtos/response/projection-technology-page.response";
import {Injectable} from "@angular/core";
import {UpdateProjectionTechnologyRequest} from "../dtos/request/update-projection-technology.request";
import {ProjectionTechnologyNameResponse} from "../dtos/response/projection-technology-name.response";
import {ProjectionTechnologyResponse} from "../dtos/response/projection-technology.response";
import {CreateProjectionTechnologyRequest} from "../dtos/request/create-projection-technology.request";

@Injectable({
  providedIn: 'root',
})
export class ProjectionTechnologyService {
  static readonly projectionTechnologiesUrl = `${environment.API_BASE_URL}/projection-technologies`;

  constructor(
    private readonly httpClient: HttpClient
  ) {
  }

  getProjectionTechnologiesPage(paginatorRequestParams: PaginatorRequestParams): Observable<ProjectionTechnologyPageResponse> {
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

  getAllProjectionTechnologies(): Observable<ProjectionTechnologyResponse[]> {
    const url = `${ProjectionTechnologyService.projectionTechnologiesUrl}/all`;
    return this.httpClient.get<ProjectionTechnologyResponse[]>(url);
  }

  getAllProjectionTechnologiesNames(): Observable<ProjectionTechnologyNameResponse[]> {
    const url = `${ProjectionTechnologyService.projectionTechnologiesUrl}/names`;
    return this.httpClient.get<ProjectionTechnologyNameResponse[]>(url);
  }

  getProjectionTechnology(technology: string): Observable<ProjectionTechnologyResponse> {
    const url = `${ProjectionTechnologyService.projectionTechnologiesUrl}/${technology}`;
    return this.httpClient.get<ProjectionTechnologyResponse>(url);
  }

  createProjectionTechnology(projectTechnology: CreateProjectionTechnologyRequest): Observable<void> {
    const url = ProjectionTechnologyService.projectionTechnologiesUrl;
    return this.httpClient.post<void>(url, projectTechnology);
  }

  updateProjectionTechnology(technology: string, projectionTechnology: UpdateProjectionTechnologyRequest) {
    const url = `${ProjectionTechnologyService.projectionTechnologiesUrl}/${technology}`;
    return this.httpClient.patch<void>(url, projectionTechnology);
  }

  deleteProjectionTechnology(technology: string): Observable<void> {
    const url = `${ProjectionTechnologyService.projectionTechnologiesUrl}/${technology}`;
    return this.httpClient.delete<void>(url);
  }
}
