import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Role} from "../../../auth/role";
import {Router} from "@angular/router";
import {RegisterUserRequest} from "../../../auth/dto/register-user.request";
import {AuthService} from "../../../auth/service/auth.service";

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
  accountAlreadyExistsError: boolean = false;

  form = new FormGroup({
    firstName: this.firstNameControl,
    lastName: this.lastNameControl,
    email: this.emailControl,
    password: this.passwordControl,
    emailConfirmation: this.emailConfirmationControl,
  });


  constructor(
    private readonly authService: AuthService,
    private readonly router: Router,
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
        this.router.navigate(['/activate-account']);
      },
      error: (error) => {
        if (error.error.errors[0] === 'User already exists') {
          this.accountAlreadyExistsError = true;
        }
      }
    })
  }

  private createRegisterUserRequest(): RegisterUserRequest {
    const {firstName, lastName, email, password} = this.form.value;
    return new RegisterUserRequest(firstName!, lastName!, email!, password!, Role.CUSTOMER);
  }
}
