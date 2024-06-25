import {FilmMemberRequest} from "./film-member.request";

export class ProductionDetailsRequest {
  constructor(
    readonly worldPremiereDate: Date,
    readonly director: FilmMemberRequest,
    readonly actors: FilmMemberRequest[],
    readonly originalLanguages: string[],
    readonly productionCountries: string[],
  ) {
  }
}
