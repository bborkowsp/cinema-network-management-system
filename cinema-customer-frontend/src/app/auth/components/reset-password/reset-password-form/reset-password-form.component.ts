import {Component, signal} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../../service/auth.service";
import {ResetPasswordRequest} from "../../../dto/reset-password.request";
import {passwordsMatchValidator} from "../../../../_shared/validators/passwords-match.validator";
import {PasswordType} from "../../../../_shared/enums/password-type.enum";
import {OpenSnackBar} from "../../../../_shared/components/snackbar/open-snack-bar";
import {SnackBarType} from "../../../../_shared/components/snackbar/snackbar-type.enum";

@Component({
  selector: 'app-reset-password-form',
  templateUrl: './reset-password-form.component.html',
  styleUrls: ['./reset-password-form.component.scss']
})
export class ResetPasswordFormComponent {
  private static readonly PASSWORD_RESET_SUCCESSFULLY_MESSAGE = "Password reset successfully!";
  private static readonly PASSWORD_RESET_FAILED_MESSAGE = "Password reset failed!";
  hideNewPassword = signal(true);
  hideNewPasswordConfirmation = signal(true);

  resetPasswordForm = new FormGroup({
    newPassword: new FormControl('', [Validators.required]),
    newPasswordConfirmation: new FormControl('', [Validators.required]),
  }, {validators: passwordsMatchValidator('newPassword', 'newPasswordConfirmation')});
  protected readonly PasswordType = PasswordType;

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router,
    private readonly route: ActivatedRoute,
    private readonly openSnackBar: OpenSnackBar
  ) {
  }

  get newPasswordControl() {
    return this.resetPasswordForm.get('newPassword') as FormControl;
  }

  get newPasswordConfirmationControl() {
    return this.resetPasswordForm.get('newPasswordConfirmation') as FormControl;
  }

  hidePassword(event: MouseEvent, passwordType: PasswordType) {
    switch (passwordType) {
      case PasswordType.NEW:
        this.hideNewPassword.set(!this.hideNewPassword());
        break;
      case PasswordType.CONFIRMATION:
        this.hideNewPasswordConfirmation.set(!this.hideNewPasswordConfirmation());
        break;
    }
    event.stopPropagation();
  }

  resetPassword() {
    const resetPasswordRequest = this.createResetPasswordRequest();
    this.authService.resetPassword(resetPasswordRequest).subscribe({
      next: () => {
        this.openSnackBar.openSnackBar(ResetPasswordFormComponent.PASSWORD_RESET_SUCCESSFULLY_MESSAGE, SnackBarType.SUCCESS);
        this.router.navigate(['/login']);
      },
      error: () => {
        this.openSnackBar.openSnackBar(ResetPasswordFormComponent.PASSWORD_RESET_FAILED_MESSAGE, SnackBarType.ERROR);
      }
    })
  }

  private createResetPasswordRequest() {
    const newPassword = this.resetPasswordForm.value.newPassword as string;
    const token = this.route.snapshot.queryParamMap.get('token') as string;
    return new ResetPasswordRequest(newPassword, token);
  }
}
