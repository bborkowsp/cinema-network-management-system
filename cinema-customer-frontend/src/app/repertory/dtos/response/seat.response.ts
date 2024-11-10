import {SeatZone} from "../../components/buy-ticket/enums/seat-zone";
import {SeatStatus} from "./seat-status";

export class SeatResponse {
  constructor(
    readonly id: number,
    readonly seatRow: number,
    readonly seatColumn: number,
    readonly seatZone: SeatZone,
    readonly status: SeatStatus[],
  ) {
  }
}

