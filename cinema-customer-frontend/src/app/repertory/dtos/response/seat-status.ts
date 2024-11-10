import {Status} from "../../components/buy-ticket/enums/status";

export class SeatStatus {
  constructor(
    readonly status: Status,
    readonly statusStart: Date,
    readonly statusEnd: Date
  ) {
  }
}
