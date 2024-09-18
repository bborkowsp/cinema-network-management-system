import {MovieVariantResponse} from "../../movie/dtos/response/movie-variant.response";

export class CreateScreeningRequest {
  constructor(
    readonly movieTitle: string,
    readonly startTime: Date,
    readonly endTime: Date,
    readonly screeningRoom: string,
    readonly email: string,
    readonly movieVariant: MovieVariantResponse
  ) {
  }
}
