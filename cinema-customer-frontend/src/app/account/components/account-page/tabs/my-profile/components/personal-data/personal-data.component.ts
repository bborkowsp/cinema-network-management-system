import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomerService} from "../../../../../../services/customer.service";
import {SnackBarType} from "../../../../../../../_shared/components/snackbar/snackbar-type.enum";
import {UserResponse} from "../../../../../../dtos/response/user.response";
import {UpdateCustomerProfileRequest} from "../../../../../../dtos/request/update-customer-profile.request";
import {MyProfileComponent} from "../../my-profile.component";

@Component({
  selector: 'app-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrl: './personal-data.component.scss'
})
export class PersonalDataComponent implements OnInit {
  private static readonly PROFILE_UPDATED_SUCCESSFULLY_MESSAGE = "Profile updated successfully!";

  profileForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.email]),
  });

  constructor(
    private readonly customerService: CustomerService,
    private myProfileComponent: MyProfileComponent
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

  ngOnInit() {
    this.customerService.getCustomer().subscribe(customer => {
      this.fillFormWithCustomerData(customer);
    });
  }

  saveProfile() {
    if (this.profileForm.valid) {
      const updateCustomerProfileRequest = this.createUpdateCustomerProfileRequest();
      this.customerService.updateCustomerProfile(updateCustomerProfileRequest).subscribe({
        next: () => {
          this.myProfileComponent.openSnackBar(PersonalDataComponent.PROFILE_UPDATED_SUCCESSFULLY_MESSAGE, SnackBarType.SUCCESS);
        },
        error: (error) => {
          this.myProfileComponent.openSnackBar(error.error.errors[0], SnackBarType.ERROR);
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
}
