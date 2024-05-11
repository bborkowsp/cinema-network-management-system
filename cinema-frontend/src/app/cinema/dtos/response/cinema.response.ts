import {AddressResponse} from "./address.response";
import {ImageResponse} from "../../../movie/dtos/response/image.response";
import {ContactDetailsResponse} from "./contact-details.response";
import {CinemaManagerTableResponse} from "../../../user/dtos/response/cinema-manager-table.response";

export class CinemaResponse {
  constructor(
    readonly name: string,
    readonly description: string,
    readonly address: AddressResponse,
    readonly image: ImageResponse,
    readonly contactDetails: ContactDetailsResponse[],
    readonly cinemaManager: CinemaManagerTableResponse,
  ) {
  }
}
