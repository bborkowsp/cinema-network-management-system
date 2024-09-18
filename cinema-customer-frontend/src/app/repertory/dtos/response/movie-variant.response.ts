import {ProjectionTechnology} from "../../enums/projection-technology";
import {Language} from "../../enums/language";

export class MovieVariantResponse {
  constructor(
    readonly projectionTechnology: ProjectionTechnology,
    readonly language: Language,
  ) {
  }
}
