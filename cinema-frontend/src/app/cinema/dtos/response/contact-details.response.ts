import {ContactTypeResponse} from "./contact-type.response";

export class ContactDetailsResponse {
  constructor(
    readonly department: string,
    readonly contactType: ContactTypeResponse,
  ) {
  }
}
