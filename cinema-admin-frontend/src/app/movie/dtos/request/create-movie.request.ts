import {ProductionDetailsRequest} from "./production-details.request";
import {SubtitleAndSoundOptionsRequest} from "./subtitle-and-sound-options.request";
import {VideoFileRequest} from "./video-file.request";
import {CreateImageRequest} from "./create-image.request";
import {
  ProjectionTechnologyResponse
} from "../../../projection-technology/dtos/response/projection-technology.response";

export class CreateMovieRequest {
  constructor(
    readonly title: string,
    readonly originalTitle: string,
    readonly duration: number,
    readonly releaseDate: Date,
    readonly description: string,
    readonly productionDetails: ProductionDetailsRequest,
    readonly subtitleAndSoundOptions: SubtitleAndSoundOptionsRequest,
    readonly ageRestriction: string,
    readonly image: CreateImageRequest,
    readonly trailer: VideoFileRequest,
    readonly genres: string[],
    readonly projectionTechnologies: ProjectionTechnologyResponse[],
  ) {
  }
}
