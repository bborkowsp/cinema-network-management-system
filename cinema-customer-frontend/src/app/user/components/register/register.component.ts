import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../auth/auth.service";
import {RegisterUserRequest} from "../../../auth/register-user.request";
import {Role} from "../../../auth/role";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  firstNameControl = new FormControl('', [Validators.required]);
  lastNameControl = new FormControl('', [Validators.required]);
  emailControl = new FormControl('', [Validators.required, Validators.email]);
  emailConfirmationControl = new FormControl('', [Validators.required, Validators.email]);
  passwordControl = new FormControl('', [Validators.required]);

  hidePassword: boolean = true;

  form = new FormGroup({
    firstName: this.firstNameControl,
    lastName: this.lastNameControl,
    email: this.emailControl,
    password: this.passwordControl,
    emailConfirmation: this.emailConfirmationControl,
  });


  constructor(
    private readonly authService: AuthService,
  ) {
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  submit() {
    const registerUserRequest = this.createRegisterUserRequest();
    this.authService.register(registerUserRequest).subscribe({
      next: () => {
        console.log('User registered')
      },
      error: () => {
        console.log('Error registering user')
      }
    })
  }

  private createRegisterUserRequest(): RegisterUserRequest {
    const {firstName, lastName, email, password} = this.form.value;
    return new RegisterUserRequest(firstName!, lastName!, email!, password!, Role.CUSTOMER);
  }
}
