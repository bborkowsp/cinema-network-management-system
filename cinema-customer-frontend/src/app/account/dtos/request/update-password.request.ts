export class UpdatePasswordRequest {
  constructor(
    readonly currentPassword: string,
    readonly newPassword: string,
  ) {
  }
}
