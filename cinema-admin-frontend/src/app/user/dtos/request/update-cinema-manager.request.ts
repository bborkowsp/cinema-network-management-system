export class UpdateCinemaManagerRequest {
  constructor(
    public readonly firstName: string,
    public readonly lastName: string,
    public readonly email: string,
    public readonly currentPassword: string,
    public readonly newPassword: string,
    public readonly managedCinemaName: string | null,
  ) {
  }
}
