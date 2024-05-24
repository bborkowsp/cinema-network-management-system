import {CreateScreeningRoomRequest} from "./create-screening-room.request";
import {CreateContactDetailsRequest} from "./create-contact-details.request";
import {CreateImageRequest} from "../../../movie/dtos/request/create-image.request";
import {CreateAddressRequest} from "./create-address.request";
import {UserResponse} from "../../../user/dtos/response/user.response";

export class CreateCinemaRequest {

  constructor(
    readonly name: string,
    readonly description: string,
    readonly address: CreateAddressRequest,
    readonly image: CreateImageRequest,
    readonly screeningRooms: CreateScreeningRoomRequest[],
    readonly contactDetails: CreateContactDetailsRequest[],
    readonly cinemaManager: UserResponse
  ) {

  }

}
