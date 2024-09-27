export class ResetPasswordRequest {
  constructor(
    readonly newPassword: string,
    readonly token: string
  ) {
  }
}
