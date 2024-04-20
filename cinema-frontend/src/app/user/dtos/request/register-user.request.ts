export class RegisterUserRequest {
  constructor(
    readonly email: string,
    readonly password: string,
    readonly firstName: string,
    readonly lastName: string,
    readonly role: string,
  ) {
  }
}
