import {Language} from "./language";
import {ProjectionTechnology} from "./projection-technology";

export class MovieVariantResponse {
  constructor(
    readonly id: number,
    readonly projectionTechnology: ProjectionTechnology,
    readonly language: Language
  ) {
  }
}
