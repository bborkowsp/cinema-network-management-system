import {CinemaResponse} from "../../../cinema/dtos/response/cinema.response";

export class CinemaManagerTableResponse {
  constructor(
    readonly firstName: string,
    readonly lastName: string,
    readonly email: string,
    readonly managedCinema: CinemaResponse,
  ) {
  }
}
