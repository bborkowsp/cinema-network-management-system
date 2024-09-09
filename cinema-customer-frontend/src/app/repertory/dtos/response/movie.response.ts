import {AgeRestrictionResponse} from "./age-restriction.response";
import {SubtitleAndSoundOptionsResponse} from "./subtitle-and-sound-options.response";
import {ProductionDetailsResponse} from "./production-details.response";
import {ProjectionTechnologyResponse} from "./projection-technology.response";

export class MovieResponse {
  constructor(
    readonly title: string,
    readonly originalTitle: string,
    readonly duration: number,
    readonly releaseDate: Date,
    readonly productionDetails: ProductionDetailsResponse,
    readonly description: string,
    readonly subtitleAndSoundOptions: SubtitleAndSoundOptionsResponse,
    readonly ageRestriction: AgeRestrictionResponse,
    readonly poster: string,
    readonly trailer: string,
    readonly genres: string[],
    readonly projectionTechnologies: ProjectionTechnologyResponse[],
  ) {
  }
}
