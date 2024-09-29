import {Role} from "../../../auth/enums/role";

export class CreateUserRequest {
  constructor(
    public readonly firstName: string,
    public readonly lastName: string,
    public readonly email: string,
    public readonly password: string,
    public readonly role: Role,
  ) {
  }
}
