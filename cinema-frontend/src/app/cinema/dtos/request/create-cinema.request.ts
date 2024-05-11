import {CreateScreeningRoomRequest} from "./create-screening-room.request";
import {ContactDetailsRequest} from "./contact-details.request";
import {CreateImageRequest} from "../../../movie/dtos/request/create-image.request";
import {CinemaManagerResponse} from "../../../user/dtos/response/cinema-manager.response";
import {CreateAddressRequest} from "./create-address.request";

export class CreateCinemaRequest {

  constructor(
    readonly name: string,
    readonly description: string,
    readonly address: CreateAddressRequest,
    readonly image: CreateImageRequest,
    readonly screeningRooms: CreateScreeningRoomRequest[],
    readonly contactDetails: ContactDetailsRequest[],
    readonly cinemaManager: CinemaManagerResponse
  ) {

  }

}
