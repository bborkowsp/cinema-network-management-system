import {environment} from "../../../assets/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {PaginatorRequestParams} from "../../shared/dtos/paginator-request-params";
import {MoviePageResponse} from "../dtos/response/movie-page.response";
import {MovieResponse} from "../dtos/response/movie.response";

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

  getMovie(title: string): Observable<MovieResponse> {
    const url = `${MovieService.moviesUrl}/${title}`;
    return this.httpClient.get<MovieResponse>(url);
  }


  deleteMovie(title: string) {
    const url = `${MovieService.moviesUrl}/${title}`;
    return this.httpClient.delete<void>(url);
  }
}
