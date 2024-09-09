export class ResetPasswordRequest {
  readonly newPassword: string;
  readonly token: string;

  constructor(password: string, token: string) {
    this.newPassword = password;
    this.token = token;
  }
}
