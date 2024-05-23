import {CreateContactTypeRequest} from "./create-contact-type.request";

export class ContactDetailsRequest {
  constructor(
    readonly department: string,
    readonly contactType: CreateContactTypeRequest,
  ) {
  }
}
