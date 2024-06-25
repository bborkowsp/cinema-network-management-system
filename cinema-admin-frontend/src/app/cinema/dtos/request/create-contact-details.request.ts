import {CreateContactTypeRequest} from "./create-contact-type.request";

export class CreateContactDetailsRequest {
  constructor(
    readonly department: string,
    readonly contactType: CreateContactTypeRequest,
  ) {
  }
}
