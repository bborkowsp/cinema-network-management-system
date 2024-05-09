import {SeatRequest} from "./seat.request";
import {
  ProjectionTechnologyResponse
} from "../../../projection-technology/dtos/response/projection-technology.response";

export class ScreeningRoomRequest {
  constructor(
    readonly rows: number,
    readonly columns: number,
    readonly seatingPlan: SeatRequest[][],
    readonly projectionTechnologies: ProjectionTechnologyResponse[],
  ) {
  }
}
