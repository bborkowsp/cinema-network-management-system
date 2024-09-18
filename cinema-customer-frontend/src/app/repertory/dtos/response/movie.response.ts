import {AgeRestrictionResponse} from "./age-restriction.response";
import {ProductionDetailsResponse} from "./production-details.response";
import {MovieVariantResponse} from "./movie-variant.response";

export class MovieResponse {
  constructor(
    readonly title: string,
    readonly originalTitle: string,
    readonly duration: number,
    readonly releaseDate: Date,
    readonly productionDetails: ProductionDetailsResponse,
    readonly description: string,
    readonly ageRestriction: AgeRestrictionResponse,
    readonly poster: string,
    readonly trailer: string,
    readonly genres: string[],
    readonly movieVariants: MovieVariantResponse[]
  ) {
  }
}
