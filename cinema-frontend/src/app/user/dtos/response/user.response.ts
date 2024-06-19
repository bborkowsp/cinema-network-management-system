export class UserResponse {
  constructor(
    readonly firstName: string,
    readonly lastName: string,
    readonly email: string,
    readonly role: string,
  ) {
  }
}
