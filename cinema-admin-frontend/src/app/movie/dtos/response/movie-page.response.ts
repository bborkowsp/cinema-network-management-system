import {MovieListResponse} from "./movie-list.response";

export class MoviePageResponse {
  constructor(
    public content: MovieListResponse[],
    public totalElements: number,
  ) {
  }
}
