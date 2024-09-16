import {SeatResponse} from "../../cinema/dtos/response/seat.response";

export class ScreeningRoomResponse {
  constructor(
    readonly name: string,
    readonly seats: SeatResponse[][]
  ) {
  }
}
