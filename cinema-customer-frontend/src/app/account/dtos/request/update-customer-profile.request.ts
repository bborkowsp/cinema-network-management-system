export class UpdateCustomerProfileRequest {
  constructor(
    readonly firstName: string,
    readonly lastName: string,
    readonly email: string,
  ) {
  }
}
