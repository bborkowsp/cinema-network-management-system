import {ScreeningRoomResponse} from "./screening-room.response";
import {MovieResponse} from "./movie.response";

export class ScreeningResponse {
  constructor(
    readonly id: number,
    readonly movie: MovieResponse,
    readonly startTime: Date,
    readonly endTime: Date,
    readonly screeningRoom: ScreeningRoomResponse
  ) {
  }
}
