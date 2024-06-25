import {CreateSeatRequest} from "./create-seat.request";
import {
  ProjectionTechnologyNameResponse
} from "../../../projection-technology/dtos/response/projection-technology-name.response";

export class CreateScreeningRoomRequest {
  constructor(
    readonly name: string,
    readonly seats: CreateSeatRequest[][],
    readonly supportedTechnologies: ProjectionTechnologyNameResponse[],
  ) {
  }
}
