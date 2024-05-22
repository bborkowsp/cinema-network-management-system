import {AddressResponse} from "./address.response";
import {ImageResponse} from "../../../movie/dtos/response/image.response";
import {ContactDetailsResponse} from "./contact-details.response";
import {ScreeningRoomResponse} from "../../../repertory/dtos/screening-room.response";
import {CinemaManagerResponse} from "../../../user/dtos/response/cinema-manager.response";

export class CinemaResponse {
  constructor(
    readonly name: string,
    readonly description: string,
    readonly address: AddressResponse,
    readonly image: ImageResponse,
    readonly screeningRooms: ScreeningRoomResponse[],
    readonly contactDetails: ContactDetailsResponse[],
    readonly cinemaManager: CinemaManagerResponse,
  ) {
  }
}
