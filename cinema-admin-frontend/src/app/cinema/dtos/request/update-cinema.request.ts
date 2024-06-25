import {CreateAddressRequest} from "./create-address.request";
import {CreateImageRequest} from "../../../movie/dtos/request/create-image.request";
import {CreateScreeningRoomRequest} from "./create-screening-room.request";
import {CreateContactDetailsRequest} from "./create-contact-details.request";
import {CinemaManagerResponse} from "../../../user/dtos/response/cinema-manager.response";

export class UpdateCinemaRequest {
  constructor(
    readonly name: string,
    readonly description: string,
    readonly address: CreateAddressRequest,
    readonly image: CreateImageRequest,
    readonly screeningRooms: CreateScreeningRoomRequest[],
    readonly contactDetails: CreateContactDetailsRequest[],
    readonly cinemaManager: CinemaManagerResponse
  ) {
  }
}
