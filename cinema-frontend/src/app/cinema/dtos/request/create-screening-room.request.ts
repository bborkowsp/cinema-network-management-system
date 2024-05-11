import {
  ProjectionTechnologyResponse
} from "../../../projection-technology/dtos/response/projection-technology.response";
import {CreateSeatRequest} from "./create-seat.request";

export class CreateScreeningRoomRequest {
  constructor(
    readonly name: string,
    readonly seats: CreateSeatRequest[][],
    readonly supportedTechnologies: ProjectionTechnologyResponse[],
  ) {
  }
}
