import {FilmMemberResponse} from "./film-member.response";

export class MovieListResponse {
  constructor(
    readonly title: string,
    readonly originalTitle: string,
    readonly duration: string,
    readonly releaseDate: Date,
    readonly poster: string,
    readonly director: FilmMemberResponse,
  ) {
  }
}
