export class LoginUserRequest {
  constructor(
    readonly email: string,
    readonly password: string,
    readonly role: string,
  ) {
  }
}
