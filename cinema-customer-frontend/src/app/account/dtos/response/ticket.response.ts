import {SeatResponse} from "../../../repertory/dtos/response/seat.response";
import {ScreeningResponse} from "../../../repertory/dtos/response/screening.response";

export class TicketResponse {
  constructor(
    readonly qrCode: string,
    readonly bookedSeats: SeatResponse[],
    readonly screening: ScreeningResponse,
    readonly cinemaName: string
  ) {
  }
}
