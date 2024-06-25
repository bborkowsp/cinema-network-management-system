import {ImageResponse} from "./image.response";
import {
  ProjectionTechnologyResponse
} from "../../../projection-technology/dtos/response/projection-technology.response";
import {AgeRestrictionResponse} from "./age-restriction.response";
import {VideoFileResponse} from "./video-file.response";
import {SubtitleAndSoundOptionsResponse} from "./subtitle-and-sound-options.response";
import {ProductionDetailsResponse} from "./production-details.response";

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
    readonly poster: ImageResponse,
    readonly trailer: VideoFileResponse,
    readonly genres: string[],
    readonly projectionTechnologies: ProjectionTechnologyResponse[],
  ) {
  }
}
