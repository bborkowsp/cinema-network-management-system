import {ProjectionTechnologyResponse} from "./projection-technology.response";
import {SeatResponse} from "./seat.response";

export class ScreeningRoomResponse {
  constructor(
    readonly name: string,
    readonly seats: SeatResponse[][],
    readonly supportedTechnologies: ProjectionTechnologyResponse[],
  ) {
  }
}
