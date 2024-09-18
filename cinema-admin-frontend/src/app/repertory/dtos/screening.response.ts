import {MovieResponse} from "../../movie/dtos/response/movie.response";
import {ScreeningRoomResponse} from "./screening-room.response";
import {MovieVariantResponse} from "../../movie/dtos/response/movie-variant.response";

export class ScreeningResponse {
  constructor(
    readonly id: number,
    readonly movie: MovieResponse,
    readonly startTime: Date,
    readonly endTime: Date,
    readonly screeningRoom: ScreeningRoomResponse,
    readonly movieVariant: MovieVariantResponse
  ) {
  }
}
