import {ProductionDetailsRequest} from "./production-details.request";
import {MovieVariantResponse} from "../response/movie-variant.response";

export class CreateMovieRequest {
  constructor(
    readonly title: string,
    readonly originalTitle: string,
    readonly duration: number,
    readonly releaseDate: Date,
    readonly description: string,
    readonly productionDetails: ProductionDetailsRequest,
    readonly ageRestriction: string,
    readonly trailer: string,
    readonly genres: string[],
    readonly movieVariants: MovieVariantResponse[],
  ) {
  }
}
