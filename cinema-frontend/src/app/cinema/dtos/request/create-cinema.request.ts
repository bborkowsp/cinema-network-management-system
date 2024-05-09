import {AddressRequest} from "./address.request";

export class CreateCinemaRequest {

  constructor(
    readonly name: string,
    readonly description: string,
    readonly address: AddressRequest,
    // readonly screeningRooms: ScreeningRoomRequest[]
  ) {

  }

}
