import {CreateSeatRequest} from "./create-seat.request";

export class CreateScreeningRoomRequest {
  constructor(
    readonly name: string,
    readonly seats: CreateSeatRequest[][]
  ) {
  }
}
