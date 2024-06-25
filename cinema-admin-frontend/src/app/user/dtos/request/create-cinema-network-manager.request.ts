export class CreateCinemaNetworkManagerRequest {
  constructor(
    public readonly firstName: string,
    public readonly lastName: string,
    public readonly email: string,
    public readonly password: string
  ) {
  }
}
