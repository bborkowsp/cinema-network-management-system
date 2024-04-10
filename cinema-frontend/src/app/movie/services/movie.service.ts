import {environment} from "../../../assets/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {MovieResponse} from "../dtos/response/movie.response";
import {PaginatorRequestParams} from "../../shared/dtos/paginator-request-params";
import {MoviePageResponse} from "../dtos/response/movie-page.response";

@Injectable({
  providedIn: 'root',
})

export class MovieService {
  static readonly moviesUrl = `${environment.apiBaseUrl}/movies`;

  constructor(private readonly httpClient: HttpClient) {
  }

  getMovies(paginatorRequestParams: PaginatorRequestParams): Observable<MoviePageResponse> {
    let params = new HttpParams()
      .set('page', paginatorRequestParams.page.toString())
      .set('size', paginatorRequestParams.size.toString());

    if (paginatorRequestParams.sort) {
      params = params.set('sort', paginatorRequestParams.sort.join(','));
    }

    return this.httpClient
      .get<MoviePageResponse>(MovieService.moviesUrl, {params})
      .pipe(map((response) => response));
  }

  getMovie(name: string): Observable<MovieResponse> {
    const url = `${MovieService.moviesUrl}/${name}`;
    return this.httpClient.get<MovieResponse>(url);
  }

  deleteMovie(name: string) {
    const url = `${MovieService.moviesUrl}/${name}`;
    return this.httpClient.delete<void>(url);
  }
}
