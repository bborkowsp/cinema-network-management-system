import {AddressResponse} from "./address.response";
import {ImageResponse} from "../../../movie/dtos/response/image.response";
import {CinemaManagerResponse} from "../../../user/dtos/response/cinema-manager.response";
import {ContactDetailsResponse} from "./contact-details.response";

export class CinemaResponse {
  constructor(
    readonly name: string,
    readonly description: string,
    readonly address: AddressResponse,
    readonly image: ImageResponse,
    readonly contactDetails: ContactDetailsResponse[],
    readonly cinemaManager: CinemaManagerResponse,
  ) {
  }
}
