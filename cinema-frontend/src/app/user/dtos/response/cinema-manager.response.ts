import {CinemaResponse} from "../../../cinema/dtos/response/cinema.response";

export class CinemaManagerResponse {
  constructor(
    readonly firstName: string,
    readonly lastName: string,
    readonly email: string,
    readonly managedCinema: CinemaResponse,
  ) {
  }
}
