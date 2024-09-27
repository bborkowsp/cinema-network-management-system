import {Component, signal} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SnackBarType} from "../../../../../../../_shared/components/snackbar/snackbar-type.enum";
import {UpdatePasswordRequest} from "../../../../../../dtos/request/update-password.request";
import {CustomerService} from "../../../../../../services/customer.service";
import {MyProfileComponent} from "../../my-profile.component";
import {passwordsMatchValidator} from "../../../../../../../_shared/validators/passwords-match.validator";
import {PasswordType} from "../../../../../../../_shared/enums/password-type.enum";


@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrl: './change-password.component.scss'
})
export class ChangePasswordComponent {
  private static readonly PASSWORD_UPDATED_SUCCESSFULLY_MESSAGE = "Password updated successfully!";
  hideCurrentPassword = signal(true);
  hideNewPassword = signal(true);
  hideNewPasswordConfirmation = signal(true);

  changePasswordForm = new FormGroup({
    oldPassword: new FormControl('', [Validators.required]),
    newPassword: new FormControl('', [Validators.required]),
    newPasswordConfirmation: new FormControl('', [Validators.required]),
  }, {validators: passwordsMatchValidator('newPassword', 'newPasswordConfirmation')});
  protected readonly PasswordType = PasswordType;

  constructor(
    private readonly customerService: CustomerService,
    private myProfileComponent: MyProfileComponent
  ) {
  }

  get currentPasswordControl() {
    return this.changePasswordForm.get('oldPassword') as FormControl;
  }

  get newPasswordControl() {
    return this.changePasswordForm.get('newPassword') as FormControl;
  }

  get newPasswordConfirmationControl() {
    return this.changePasswordForm.get('newPasswordConfirmation') as FormControl;
  }

  hidePassword(event: MouseEvent, passwordType: PasswordType) {
    switch (passwordType) {
      case PasswordType.CURRENT:
        this.hideCurrentPassword.set(!this.hideCurrentPassword());
        break;
      case PasswordType.NEW:
        this.hideNewPassword.set(!this.hideNewPassword());
        break;
      case PasswordType.CONFIRMATION:
        this.hideNewPasswordConfirmation.set(!this.hideNewPasswordConfirmation());
        break;
    }
    event.stopPropagation();
  }

  changePassword() {
    if (this.changePasswordForm.valid) {
      const updatePasswordRequest = this.createUpdatePasswordRequest();
      this.customerService.updatePassword(updatePasswordRequest).subscribe({
        next: () => {
          this.myProfileComponent.openSnackBar(ChangePasswordComponent.PASSWORD_UPDATED_SUCCESSFULLY_MESSAGE, SnackBarType.SUCCESS);
        },
        error: (error) => {
          this.myProfileComponent.openSnackBar(error.error.errors[0], SnackBarType.ERROR);
        }
      });
    }
  }

  private createUpdatePasswordRequest() {
    return new UpdatePasswordRequest(
      this.currentPasswordControl.value,
      this.newPasswordControl.value,
    )
  }
}
