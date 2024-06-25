import {SeatResponse} from "./seat.response";

export class SeatRowResponse {
  constructor(
    readonly columnSeats: SeatResponse[],
  ) {
  }
}
