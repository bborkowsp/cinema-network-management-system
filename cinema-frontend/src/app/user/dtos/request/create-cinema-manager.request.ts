export class CreateCinemaManagerRequest {
  constructor(
    public readonly firstName: string,
    public readonly lastName: string,
    public readonly email: string,
    public readonly password: string,
    public readonly managedCinemaName: string
  ) {
  }
}
