import {MovieListResponse} from "./movie-list.response";

export class MoviePageResponse {
  constructor(
    content: MovieListResponse[],
    totalElements: number,
  ) {
  }
}
