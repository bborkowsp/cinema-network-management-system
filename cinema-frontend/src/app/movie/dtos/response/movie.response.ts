import {ImageResponse} from "./image.response";

export class MovieResponse {
  constructor(
    readonly title: string,
    readonly image: ImageResponse,
  ) {
  }
}
