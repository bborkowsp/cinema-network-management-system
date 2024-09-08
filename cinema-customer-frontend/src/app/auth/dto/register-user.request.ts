import {Role} from "../enums/role";

export class RegisterUserRequest {
  constructor(
    readonly firstName: string,
    readonly lastName: string,
    readonly email: string,
    readonly password: string,
    readonly role: Role
  ) {
  }
}
