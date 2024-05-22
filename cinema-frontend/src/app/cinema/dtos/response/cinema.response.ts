import {AddressResponse} from "./address.response";
import {ImageResponse} from "../../../movie/dtos/response/image.response";
import {ContactDetailsResponse} from "./contact-details.response";
import {ScreeningRoomResponse} from "../../../repertory/dtos/screening-room.response";
import {UserResponse} from "../../../user/dtos/response/user.response";

export class CinemaResponse {
  constructor(
    readonly name: string,
    readonly description: string,
    readonly address: AddressResponse,
    readonly image: ImageResponse,
    readonly screeningRooms: ScreeningRoomResponse[],
    readonly contactDetails: ContactDetailsResponse[],
    readonly cinemaManager: UserResponse,
  ) {
  }
}
