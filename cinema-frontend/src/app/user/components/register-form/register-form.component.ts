import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {RegisterUserRequest} from "../../dtos/request/register-user.request";
import {createPasswordStrengthValidator} from "../../../shared/validators/password-strength-validator";


@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.scss']
})
export class RegisterFormComponent {
  protected readonly registerFormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    repeatEmail: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, createPasswordStrengthValidator()]),
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    role: new FormControl('', [Validators.required,]),
  },);

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router,
  ) {
  }

  protected onSubmit(): void {
    const {email, password, firstName, lastName, role} = this.registerFormGroup.value;
    const registerUserRequest = new RegisterUserRequest(email ?? '', password ?? '', firstName ?? '', lastName ?? '', role ?? '');
    const register$ = this.authService.register(registerUserRequest);
    register$.subscribe({
      next: () => this.goToHome(),
    });
  }


  private goToHome(): void {
    this.router.navigate(['/']);
  }
}
