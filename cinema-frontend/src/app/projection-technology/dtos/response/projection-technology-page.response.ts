import {ProjectionTechnologyResponse} from "./projection-technology.response";

export class ProjectionTechnologyPageResponse {
  constructor(
    public content: ProjectionTechnologyResponse[],
    public totalElements: number,
  ) {
  }
}
