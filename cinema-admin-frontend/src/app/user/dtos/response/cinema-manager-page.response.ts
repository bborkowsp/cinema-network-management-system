import {CinemaManagerTableResponse} from "./cinema-manager-table.response";

export class CinemaManagerPageResponse {
  constructor(
    public content: CinemaManagerTableResponse[],
    public totalElements: number,
  ) {
  }
}
