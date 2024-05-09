import {AddressRequest} from "./address.request";
import {ScreeningRoomRequest} from "./screening-room.request";
import {ContactDetailsRequest} from "./contact-details.request";

export class CreateCinemaRequest {

  constructor(
    readonly name: string,
    readonly description: string,
    readonly address: AddressRequest,
    readonly screeningRooms: ScreeningRoomRequest[],
    readonly contactDetails: ContactDetailsRequest[],
  ) {

  }

}
