import {ScreeningRoomResponse} from "./screening-room.response";

export class ScreeningTimeResponse {
  constructor(
    readonly screeningRoom: ScreeningRoomResponse,
    readonly date: Date
  ) {
  }
}
