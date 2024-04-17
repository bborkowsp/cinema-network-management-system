import {FilmMemberResponse} from "./film-member.response";

export class ProductionDetailsResponse {
  constructor(
    readonly worldPremiereDate: Date,
    readonly director: FilmMemberResponse,
    readonly actors: FilmMemberResponse[],
    readonly originalLanguages: string[],
    readonly productionCountries: string[],
  ) {
  }

}
