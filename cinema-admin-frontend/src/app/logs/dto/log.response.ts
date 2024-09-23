export class LogResponse {
  constructor(
    readonly timestamp: string,
    readonly level: string,
    readonly logger: string,
    readonly message: string,
  ) {
  }
}
