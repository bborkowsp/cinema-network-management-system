import {Component, signal} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {RegisterUserRequest} from "../../dto/register-user.request";
import {AuthService} from "../../service/auth.service";
import {Role} from "../../enums/role";
import {passwordsMatchValidator} from "../../../_shared/validators/passwords-match.validator";
import {SnackBarType} from "../../../_shared/components/snackbar/snackbar-type.enum";
import {SnackBarComponent} from "../../../_shared/components/snackbar/snack-bar.component";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackBarData} from "../../../_shared/components/snackbar/snackbar-data.interface";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  hidePassword = signal(true);
  hidePasswordConfirmation = signal(true);

  registerForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
    passwordConfirmation: new FormControl('', [Validators.required]),
  }, {validators: passwordsMatchValidator('password', 'passwordConfirmation')});

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router,
    private snackBar: MatSnackBar,
  ) {
  }

  get firstNameControl() {
    return this.registerForm.get('firstName') as FormControl;
  }

  get lastNameControl() {
    return this.registerForm.get('lastName') as FormControl;
  }

  get emailControl() {
    return this.registerForm.get('email') as FormControl;
  }

  get passwordControl() {
    return this.registerForm.get('password') as FormControl;
  }

  get passwordConfirmationControl() {
    return this.registerForm.get('passwordConfirmation') as FormControl;
  }

  showPasswordEvent(event: MouseEvent) {
    this.hidePassword.set(!this.hidePassword());
    event.stopPropagation();
  }

  showPasswordConfirmationEvent(event: MouseEvent) {
    this.hidePasswordConfirmation.set(!this.hidePasswordConfirmation());
    event.stopPropagation();
  }

  submit() {
    const registerUserRequest = this.createRegisterUserRequest();
    this.authService.register(registerUserRequest).subscribe({
      next: () => {
        this.router.navigate(['/activate-account']);
      },
      error: (error) => {
        if (error.error.errors[0] === 'User already exists') {
          this.openSnackBar(error.error.errors[0], SnackBarType.ERROR);
        }
      }
    })
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

  private createRegisterUserRequest(): RegisterUserRequest {
    return new RegisterUserRequest(
      this.firstNameControl.value,
      this.lastNameControl.value,
      this.emailControl.value,
      this.passwordControl.value,
      Role.CUSTOMER
    );
  }
}
