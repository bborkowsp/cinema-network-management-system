import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../../services/user.service";
import {UserResponse} from "../../../../dtos/response/user.response";
import {UpdateCustomerProfileRequest} from "../../../../dtos/request/update-customer-profile.request";
import {UpdatePasswordRequest} from "../../../../dtos/request/update-password.request";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackBarType} from "../../../../../_shared/components/snackbar/snackbar-type.enum";
import {SnackBarComponent} from "../../../../../_shared/components/snackbar/snack-bar.component";
import {SnackBarData} from "../../../../../_shared/components/snackbar/snackbar-data.interface";

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.scss']
})
export class MyProfileComponent implements OnInit {
  successMessage: string = '';
  profileForm = new FormGroup({
    firstName: new FormControl('',),
    lastName: new FormControl('',),
    email: new FormControl('', [Validators.email]),
  });

  resetPasswordForm = new FormGroup({
    oldPassword: new FormControl('', [Validators.required]),
    newPassword: new FormControl('', [Validators.required]),
    newPasswordConfirmation: new FormControl('', [Validators.required]),
  });

  constructor(
    private readonly userService: UserService,
    private snackBar: MatSnackBar
  ) {
  }

  get firstNameControl() {
    return this.profileForm.get('firstName') as FormControl;
  }

  get lastNameControl() {
    return this.profileForm.get('lastName') as FormControl;
  }

  get emailControl() {
    return this.profileForm.get('email') as FormControl;
  }

  get currentPasswordControl() {
    return this.resetPasswordForm.get('oldPassword') as FormControl;
  }

  get newPasswordControl() {
    return this.resetPasswordForm.get('newPassword') as FormControl;
  }

  get newPasswordConfirmationControl() {
    return this.resetPasswordForm.get('newPasswordConfirmation') as FormControl;
  }

  ngOnInit() {
    this.userService.getCustomer().subscribe(customer => {
      this.fillFormWithCustomerData(customer);
    });
  }

  saveProfile() {
    if (this.profileForm.valid) {
      const updateCustomerProfileRequest = this.createUpdateCustomerProfileRequest();
      this.userService.updateCustomerProfile(updateCustomerProfileRequest).subscribe({
        next: () => {
          this.openSnackBar("Profile updated successfully!", SnackBarType.SUCCESS);
        },
        error: (error) => {
          this.openSnackBar(error.error.errors[0], SnackBarType.ERROR);
        }
      });
    }
  }

  resetPassword() {
    if (this.resetPasswordForm.valid) {
      const updatePasswordRequest = this.createUpdatePasswordRequest();
      this.userService.updatePassword(updatePasswordRequest).subscribe({
        next: (response) => {
          this.openSnackBar("Profile updated successfully!", SnackBarType.SUCCESS);
        },
        error: (error) => {
          this.openSnackBar(error.error.errors[0], SnackBarType.ERROR);
        }
      });
    }
  }

  private fillFormWithCustomerData(customer: UserResponse) {
    this.profileForm.setValue({
      firstName: customer.firstName,
      lastName: customer.lastName,
      email: customer.email,
    });
  }

  private createUpdateCustomerProfileRequest() {
    return new UpdateCustomerProfileRequest(
      this.firstNameControl.value,
      this.lastNameControl.value,
      this.emailControl.value
    )
  }

  private createUpdatePasswordRequest() {
    return new UpdatePasswordRequest(
      this.currentPasswordControl.value,
      this.newPasswordControl.value,
    )
  }

  private openSnackBar(message: string, snackBarType: SnackBarType) {
    this.snackBar.openFromComponent(SnackBarComponent, {
      duration: 5000,
      data: {
        message,
        snackBarType,
      } as SnackBarData,
    });
  }
}
