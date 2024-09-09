import {MovieResponse} from "./movie.response";
import {ScreeningResponse} from "./screening.response";

export class ScreeningDetailsResponse {

  constructor(
    readonly movie: MovieResponse,
    readonly screenings: { [cinemaName: string]: ScreeningResponse[] }
  ) {
  }
}
