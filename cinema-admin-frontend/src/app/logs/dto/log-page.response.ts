import {LogResponse} from "./log.response";

export class LogPageResponse {
  constructor(
    public content: LogResponse[],
    public totalElements: number,
  ) {
  }
}
