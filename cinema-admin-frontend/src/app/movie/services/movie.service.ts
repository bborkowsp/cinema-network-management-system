import {environment} from "../../../assets/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {PaginatorRequestParams} from "../../_shared/dtos/paginator-request-params";
import {MoviePageResponse} from "../dtos/response/movie-page.response";
import {MovieResponse} from "../dtos/response/movie.response";
import {CreateMovieRequest} from "../dtos/request/create-movie.request";
import {UpdateMovieRequest} from "../dtos/request/update-movie.request";

@Injectable({
  providedIn: 'root',
})

export class MovieService {
  static readonly MOVIES_API_URL = `${environment.API_BASE_URL}/movies`;

  constructor(
    private readonly httpClient: HttpClient
  ) {
  }

  getMovies(paginatorRequestParams: PaginatorRequestParams): Observable<MoviePageResponse> {
    let params = new HttpParams()
      .set('page', paginatorRequestParams.page.toString())
      .set('size', paginatorRequestParams.size.toString());

    if (paginatorRequestParams.sort) {
      params = params.set('sort', paginatorRequestParams.sort.join(','));
    }

    return this.httpClient
      .get<MoviePageResponse>(MovieService.MOVIES_API_URL, {params})
      .pipe(map((response) => response));
  }

  getAllMovieTitles() {
    const url = `${MovieService.MOVIES_API_URL}/titles`;
    return this.httpClient.get<{ content: string[] }>(url).pipe(
      map((response) => response.content),
    );
  }

  getGenres(): Observable<string[]> {
    return this.httpClient.get<string[]>(`${MovieService.MOVIES_API_URL}/genres`);
  }

  getAgeRestrictions() {
    return this.httpClient.get<string[]>(`${MovieService.MOVIES_API_URL}/age-restrictions`);
  }

  getMovie(title: string): Observable<MovieResponse> {
    const url = `${MovieService.MOVIES_API_URL}/${title}`;
    return this.httpClient.get<MovieResponse>(url);
  }


  createMovie(createMovieRequest: CreateMovieRequest) {
    return this.httpClient.post<void>(MovieService.MOVIES_API_URL, createMovieRequest);
  }

  updateMovie(title: string, movie: null | UpdateMovieRequest) {
    const url = `${MovieService.MOVIES_API_URL}/${title}`;
    return this.httpClient.patch<void>(url, movie);
  }

  deleteMovie(title: string) {
    const url = `${MovieService.MOVIES_API_URL}/${title}`;
    return this.httpClient.delete<void>(url);
  }
}
