import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {LoginUserRequest} from "../../dtos/request/login-user.request";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {
  protected readonly loginFormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(
    private readonly authService: AuthService,
  ) {
  }

  protected onSubmit(): void {
    const {email, password} = this.loginFormGroup.value;
    const loginUserRequest = new LoginUserRequest(email ?? '', password ?? '');
    this.authService.login(loginUserRequest);
  }
}
