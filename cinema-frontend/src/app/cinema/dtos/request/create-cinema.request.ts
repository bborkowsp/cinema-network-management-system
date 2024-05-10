import {AddressRequest} from "./address.request";
import {ScreeningRoomRequest} from "./screening-room.request";
import {ContactDetailsRequest} from "./contact-details.request";
import {CreateImageRequest} from "../../../movie/dtos/request/create-image.request";

export class CreateCinemaRequest {

  constructor(
    readonly name: string,
    readonly description: string,
    readonly address: AddressRequest,
    readonly image: CreateImageRequest,
    readonly screeningRooms: ScreeningRoomRequest[],
    readonly contactDetails: ContactDetailsRequest[],
  ) {

  }

}
