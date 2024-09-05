import {CreateScreeningRoomRequest} from "./create-screening-room.request";
import {CreateContactDetailsRequest} from "./create-contact-details.request";
import {CreateAddressRequest} from "./create-address.request";
import {UserResponse} from "../../../user/dtos/response/user.response";

export class CreateCinemaRequest {
  constructor(
    readonly name: string,
    readonly description: string,
    readonly address: CreateAddressRequest,
    readonly screeningRooms: CreateScreeningRoomRequest[],
    readonly contactDetails: CreateContactDetailsRequest[],
    readonly cinemaManager: UserResponse
  ) {
  }
}
