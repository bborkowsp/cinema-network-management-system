import {ProjectionTechnologyResponse} from "../../projection-technology/dtos/response/projection-technology.response";
import {SeatRowResponse} from "../../cinema/dtos/response/seat-row.respones";

export class ScreeningRoomResponse {
  constructor(
    readonly name: string,
    readonly seatRows: SeatRowResponse[],
    readonly supportedTechnologies: ProjectionTechnologyResponse[],
  ) {
  }
}
